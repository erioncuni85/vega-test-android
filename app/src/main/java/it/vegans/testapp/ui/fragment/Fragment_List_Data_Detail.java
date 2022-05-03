package it.vegans.testapp.ui.fragment;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import it.vegans.testapp.R;

public class Fragment_List_Data_Detail  extends Fragment {
    TextView TitleFile,DateFile;
    PDFView viewData;
    String TitleString,DateString,PdfUrlString;
    Uri pdfUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_detail_layout, container, false);
        viewData = v.findViewById(R.id.pdfView);
        init(v);

        return v;
    }


    public void init(View insideview){
        TitleString = this.getArguments().getString("data_title");
        DateString = this.getArguments().getString("data_media_date");
        PdfUrlString = this.getArguments().getString("data_media_url");
            pdfUri = Uri.parse(PdfUrlString);
           // System.out.println("DATA-->"+pdfUri);
            TitleFile = insideview.findViewById(R.id.title_file);
            DateFile = insideview.findViewById(R.id.date_file);
            TitleFile.setText(TitleString);
            DateFile.setText("Date: "+DateString.substring(0, 16));
            new RetrivePDFfromUrl().execute(PdfUrlString);

    }

    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            viewData.fromStream(inputStream).load();
        }
    }
}
