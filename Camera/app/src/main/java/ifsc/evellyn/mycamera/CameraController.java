package ifsc.evellyn.mycamera;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.camera.view.video.OutputFileOptions;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class CameraController {
    private ListenableFuture<ProcessCameraProvider> listenableFuture;
    PreviewView previewView;
    Context context;
    ImageCapture imageCapture;

    public CameraController(PreviewView previewView, Context context) throws ExecutionException, InterruptedException {
        this.previewView = previewView;
        this.context=context;
        listenableFuture = ProcessCameraProvider.getInstance(context);


        ProcessCameraProvider cameraProvider = listenableFuture.get();
        Preview preview =startPreviewView();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        cameraProvider.unbindAll();

        imageCapture =startImageCapture();

       cameraProvider.bindToLifecycle((LifecycleOwner) context,
               selecionarCamera(),
               preview,imageCapture);
    };


    public Preview startPreviewView(){
        return new Preview.Builder().build();
    }
    public ImageCapture startImageCapture(){
        return new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY).build();
    }

    //CameraSelector
    public CameraSelector selecionarCamera(){
        return new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT).build();
    };

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void tiraFoto(){
        File file = new File(getFilePath());
        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();

        imageCapture.takePicture(
                outputFileOptions
                , context.getMainExecutor(),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                       // Toast.makeText("Foto tirada com sucesso", Toast.LENGTH_LONG).show();
                        Log.d("Foto","Foto tirada com sucesso"+outputFileResults.getSavedUri().toString());
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Log.d("Foto","Foto tirando com fracasso");
                    }
                }

        );
    }
    private String getFilePath() {
        //caso opte por usar local diferente para armazenar tem que fericair se exite o diretório
        File photoDir = new File("/sdcard/DCIM");
        if (!photoDir.exists()) {
            photoDir.mkdir();
        }

        // Pasta fotos do sistema

        //Recuperando contexto da aplicação para adquirir pastas destino
        ContextWrapper contextWrapper = new ContextWrapper(context);
        // File filePhoto = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DCIM);
        File filePhoto = contextWrapper.getExternalFilesDir(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());

        //Criando nome do arquivo com base na data e hora atual.

        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        //Criando  um arquivo temporario
        //return  filePhoto+"/"+timestamp+".jpg";
        Log.d("File",MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
        File file = new File(filePhoto, timestamp + ".jpg");
        return file.getPath();

    }
}