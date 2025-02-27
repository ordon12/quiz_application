package com.example.quizapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new
                                                                 NavigationView.OnNavigationItemSelectedListener() {
                                                                     @Override
                                                                     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                                         switch (item.getItemId()) {
                                                                             // COMMON
                                                                             case R.id.facebookPage:
                                                                                 break;
                                                                             case R.id.website:
                                                                                 break;
                                                                             case R.id.nav_privacy_policy:
                                                                                 break;
                                                                             case R.id.nav_terms_conditions:
                                                                                 break;
                                                                             case R.id.more:
                                                                                 break;
                                                                             // COMMON
                                                                             // IMPORTANT //
                                                                             case R.id.nav_rate:


                                                                                 Uri uri = Uri.parse("market://details?id=" + getApplication().getPackageName());
                                                                                 Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                                                                 // To count with Play market backstack, After pressing back button,
                                                                                 // to taken back to our application, we need to add following flags to intent.
                                                                                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                                                     goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                                                                             Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                                                                             Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                                                                 }
                                                                                 try {
                                                                                     startActivity(goToMarket);
                                                                                 } catch (
                                                                                         ActivityNotFoundException e) {
                                                                                     startActivity(new Intent(Intent.ACTION_VIEW,
                                                                                             Uri.parse("http://play.google.com/store/apps/details?id=" + getApplication().getPackageName())));
                                                                                 }

                                                                                 break;
                                                                             // IMPORTANT //
                                                                             // IMPORTANT //
                                                                             case R.id.nav_share:

                                                                                 Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                                                                 shareIntent.setType("text/plain");
                                                                                 shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Quiz App");
                                                                                 String shareMessage= "This Is Best Application For Quiz App.\n\n";
                                                                                 shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
                                                                                 shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                                                                                 startActivity(Intent.createChooser(shareIntent, "choose one"));

                                                                                 break;
                                                                             // IMPORTANT //
                                                                         }
                                                                         return true;
                                                                     }
                                                                 });

}



    public void C(View view) {

        Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
        startActivity(intent);


    }

    public void Cplus(View view) {
        Intent intent = new Intent(MainActivity.this,QuestionActivity2.class);
        startActivity(intent);
    }

    public void Python(View view) {
        Intent intent = new Intent(MainActivity.this,QuestionActivity3.class);
        startActivity(intent);
    }

    public void Html(View view) {
        Intent intent = new Intent(MainActivity.this,QuestionActivity4.class);
        startActivity(intent);
    }

    public void Kotlin(View view) {
        Intent intent = new Intent(MainActivity.this,QuestionActivity5.class);
        startActivity(intent);
    }

    public void CSharp(View view) {
        Intent intent = new Intent(MainActivity.this,QuestionActivity6.class);
        startActivity(intent);
    }
}