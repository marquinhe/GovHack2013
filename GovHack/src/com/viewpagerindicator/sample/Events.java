package com.viewpagerindicator.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.text.Html;
import android.text.method.LinkMovementMethod;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.lang.Override;
import java.util.concurrent.ArrayBlockingQueue;
import org.jsoup.select.Elements;


public final class Events extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";

    private String mContent = "???";

    public static Events newInstance(String query) {
        Events event = new Events();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1; i++) {
            builder.append(query).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        event.mContent = builder.toString();

        return event;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setGravity(Gravity.LEFT);
        tv.setText(mContent);

       String str = "<html>\n" +
               "  <head>\n" +
               "  <title></title>\n" +
               "    <meta name='viewport' content='initial-scale=1.0, user-scalable=no' />\n" +
               "    <style type='text/css'>\n" +
               "      html { height: 100% }\n" +
               "      body { height: 100%; margin: 0; padding: 0 }\n" +
               "      #map-canvas { height: 100% }\n" +
               "    </style>\n" +
               "    <script type='text/javascript'\n" +
               "      src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDEYbqdhp8aygT32d7kucv6BXC9rjpuigA&sensor=false'>\n" +
               "    </script>\n" +
               "  <script type='text/javascript'>\n" +
               "      var h1 = -34.9820;\n" +
               "      var h2 = 138.5160;\n" +
               "      var h3 = -34.9333;\n" +
               "      var h4 = 138.5833;\n" +
               "\n" +
               "      var directionsDisplay;\n" +
               "      var directionsService = new google.maps.DirectionsService();\n" +
               "      var map;\n" +
               "      var haight = new google.maps.LatLng(-34.9820, 138.5160);\n" +
               "      var oceanBeach = new google.maps.LatLng(-34.9333, 138.5833);\n" +
               "\n" +
               "      function initialize() {\n" +
               "          directionsDisplay = new google.maps.DirectionsRenderer();\n" +
               "          var mapOptions = {\n" +
               "              zoom: 14,\n" +
               "              mapTypeId: google.maps.MapTypeId.ROADMAP,\n" +
               "              center: haight\n" +
               "          }\n" +
               "          map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);\n" +
               "          directionsDisplay.setMap(map);\n" +
               "      }\n" +
               "\n" +
               "      function calcRoute(lat1, lan1, lat2, lan2) {\n" +
               "          var start = new google.maps.LatLng(-34.9820, 138.5160);\n" +
               "          var end = new google.maps.LatLng(-34.9333, 138.5833);\n" +
               "          var selectedMode = document.getElementById('mode').value;\n" +
               "          var request = {\n" +
               "              origin: start,\n" +
               "              destination: end,\n" +
               "              // Note that Javascript allows us to access the constant\n" +
               "              // using square brackets and a string value as its\n" +
               "              // 'property.'\n" +
               "              travelMode: google.maps.TravelMode[selectedMode]\n" +
               "          };\n" +
               "          directionsService.route(request, function (response, status) {\n" +
               "              if (status == google.maps.DirectionsStatus.OK) {\n" +
               "                  directionsDisplay.setDirections(response);\n" +
               "              }\n" +
               "          });\n" +
               "      }\n" +
               "\n" +
               "      google.maps.event.addDomListener(window, 'load', initialize);\n" +
               "\n" +
               "    </script>\n" +
               "  </head>\n" +
               "  <body>\n" +
               "    <div id='panel'>\n" +
               "    <b>Mode of Travel: </b>\n" +
               "    <select id='mode' onchange='calcRoute(h1,h2,h3,h4);'>\n" +
               "      <option value='DRIVING'>Driving</option>\n" +
               "      <option value='WALKING'>Walking</option>\n" +
               "      <option value='BICYCLING'>Bicycling</option>\n" +
               "      <option value='TRANSIT'>Transit</option>\n" +
               "    </select>\n" +
               "    </div>\n" +
               "    <div id='map-canvas'></div>\n" +
               "  </body>\n" +
               "</html>";

        tv.setText(Html.fromHtml(str));
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        Button button = new Button(getActivity());
        LinearLayout layout = new LinearLayout(getActivity());

        button.setText(mContent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("mainActivity");
            }
        });
        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);


        if (mContent.matches("1"))
        {
            //layout.addView(tv);
            layout.setBackgroundResource(R.drawable.map3);

        }

        else if (mContent.matches("2"))
        {
            //layout.addView(tv);
            layout.setBackgroundResource(R.drawable.map);

        }else if (mContent.matches("3")) {

            layout.setBackgroundResource(R.drawable.map);
            layout.addView(button);
        }



        return layout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }

    public String getEvents(){

     try{

         RetreiveHtml html = new RetreiveHtml();
         Document doc = Jsoup.parse(html.doInBackground("http://www.sa.lasa.asn.au/course-schedule-and-events/"));

         Elements head = doc.getElementsByTag("thead");
         Elements body = doc.getElementsByTag("tbody");

         TextView txt =  new TextView(getActivity());
         txt.setText("Executed");


         return ""+ head;

    }catch (Exception e)

    {

        return "<p>Error</p>";
    }

    }


}
