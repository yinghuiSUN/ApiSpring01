package net.javaguides.springboot_search_rest_api.service.impl;

import net.javaguides.springboot_search_rest_api.dto.TaskDto;
import net.javaguides.springboot_search_rest_api.service.ExportTaskService;
import net.javaguides.springboot_search_rest_api.service.TaskService;
import net.javaguides.springboot_search_rest_api.service.UtilisateurService;
import net.javaguides.springboot_search_rest_api.utils.Util;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportTaskServiceImpl implements ExportTaskService {
    public static final String[] displayColumnTask = {"id", "Title", "Description", "Priority", "Status", "dueDate"};

    private final UtilisateurService utilisateurService;
    private final TaskService taskService;

    public ExportTaskServiceImpl(UtilisateurService utilisateurService, TaskService taskService) {
        this.utilisateurService = utilisateurService;
        this.taskService = taskService;
    }

    @Override
    public void exportTaskTOExcelByIdUser(final Long idUser) throws IOException {

        // creer le fichier
        Workbook workbook = new XSSFWorkbook();
        // créer le sheet
        Sheet sheet = workbook.createSheet("Task");

        // =========================
        // STYLE HEADER
        // =========================
        CellStyle headerStyle = createStyleHeader(workbook);

        // Bordures
        createHeaderBordure(headerStyle);

        // creer d'abord le row 0
        createEnteteExcel(idUser, sheet);


        // créer un header au row 2
        Row headerRow = sheet.createRow(2);
        Util.createHeaderExcel(headerRow, displayColumnTask, 0, headerStyle);


        // prendre les resultats de recherche
        List<TaskDto> taskList = taskService.getFiltreTaskByUserId(idUser, null, null, null);

        createRowWithData(taskList, sheet, 3);

        // =========================
        // NOM FICHIER DYNAMIQUE
        // =========================
        String fullPath = createFileName();

        // =========================
        // ÉCRITURE
        // =========================
        FileOutputStream fileOut = new FileOutputStream(fullPath);
        workbook.write(fileOut);

        fileOut.close();
        workbook.close();

    }

    private void createEnteteExcel(Long idUser, Sheet sheet) {
        Row row0 = sheet.createRow(0);
        row0.createCell(2).setCellValue("Name");
        if (utilisateurService.findUserById(idUser)!= null) {
            row0.createCell(3).setCellValue(utilisateurService.findUserById(idUser).getName());
        }

    }

    private static void createHeaderBordure(CellStyle headerStyle) {
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
    }

    private static @NonNull CellStyle createStyleHeader(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        return headerStyle;
    }

    private static void createRowWithData(List<TaskDto> taskList, Sheet sheet, int rowNum) {
        if (!taskList.isEmpty()) {
            for (TaskDto dto : taskList) {
                Row row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(dto.getId());
                row.createCell(1).setCellValue(dto.getTitle());
                row.createCell(2).setCellValue(dto.getDescription());
                row.createCell(3).setCellValue(dto.getPriority().toString());
                row.createCell(4).setCellValue(dto.getStatus().toString());
                row.createCell(5).setCellValue(Util.formatDateToString(dto.getDueDate()));
                rowNum++;

            }
        }
        // Ajuster largeur colonnes automatiquement
        for (int i = 0; i < displayColumnTask.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    @Override
    public void exportTaskWithFiltre(List<TaskDto> list) throws IOException {
        // creer le fichier
        Workbook workbook = new XSSFWorkbook();
        // créer le sheet
        Sheet sheet = workbook.createSheet("Task");

        // =========================
        // STYLE HEADER
        // =========================
        CellStyle headerStyle = createStyleHeader(workbook);
        Long idUser = -1L;
        // Bordures
        createHeaderBordure(headerStyle);
        if (!list.isEmpty()) {
            idUser = list.get(0).getUserId();
        }
        createEnteteExcel(idUser,sheet);
        // créer un header au row 2
        Row headerRow = sheet.createRow(2);
        Util.createHeaderExcel(headerRow, displayColumnTask, 0, headerStyle);

        createRowWithData(list, sheet, 3);
        // =========================
        // NOM FICHIER DYNAMIQUE
        // =========================
        String fullPath = createFileName();

        // =========================
        // ÉCRITURE
        // =========================
        FileOutputStream fileOut = new FileOutputStream(fullPath);
        workbook.write(fileOut);

        fileOut.close();
        workbook.close();
    }

    private static @NonNull String createFileName() {
        String fileName = "tasks_" +
                java.time.LocalDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                + ".xlsx";
        return "C:/ApiProjet" + File.separator + fileName;

    }
}
