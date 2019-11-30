package com.newmantech.appcliente.utils;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

public class TemplatePDF {
    private Context context;
    public File pdfFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph; // parrafo
    private Image ivPrueba;

    // formatos
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public TemplatePDF(Context context, Image ivPrueba) {
        this.context = context;
        this.ivPrueba = ivPrueba;
    }

    private void createFile(){
        // DIRECCIÓN DE SD - NOMBRE DE FOLDER
        File folder = new File(Environment.getExternalStorageDirectory().toString(), "PDF");


        if(!folder.exists())
            folder.mkdir();

        String s = String.valueOf(System.currentTimeMillis());
        pdfFile = new File(folder, "Logo"+s+".pdf");
    }

    // cuando se agregan valores al pdf son como metadatos
    public void openDocument(){
        createFile();
        try{
            document = new Document(PageSize.A4);// Creamos el documento
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            // abrimos documento
            document.open();
        }catch (Exception e){
            Log.e("opendocument", e.toString());
        }
    }

    public void closeDocument(){
        document.close();
    }



    public void addParagraph(String text){
        try{
            paragraph = new Paragraph(text, fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("adtitles ", e.toString());
        }
    }

    public void addImage(){
        try{
            document.add((Element) ivPrueba);
        }catch (Exception e){
            Log.e("adtitles ", e.toString());
        }
    }

    public void appViewPdf(Activity activity){
        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(pdfFile.exists()){
            Uri uri = Uri.fromFile(pdfFile);//
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(uri, "application/pdf");
            try{
                activity.startActivity(i);
            }catch (ActivityNotFoundException e){
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.adobe.reader")));
                Log.e("appViewPdf", e.toString());
                Toast.makeText(activity.getApplicationContext(), "no cuentas con una aplicación para visualizar PDF", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(activity, "no se encontró el documento", Toast.LENGTH_SHORT).show();
        }
    }
}
