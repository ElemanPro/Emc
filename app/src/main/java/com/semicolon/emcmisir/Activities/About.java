package com.semicolon.emcmisir.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.semicolon.emcmisir.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_about);
       loadAbout();


    }

    public void loadAbout() {
        final FrameLayout flHolder = (FrameLayout)findViewById(R.id.about);

        AboutBuilder builder = AboutBuilder.with(this)
                .setAppIcon(R.drawable.semi)
                .setAppName("Semi Colon")
                .setPhoto(R.drawable.emc)
                .setCover(R.drawable.pic)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("مركز الايمان للصيانة ")
                .setSubTitle(" مركز الإيمان للصيانة المعتمدة بالبتانون ")
                .setLinksColumnsCount(4)
                .setBrief(" مركز الإيمان للصيانة المعتمدة وكلاء صيانة معتمدون ومتخصصون في مجال صيانة الأجهزة المنزلية والكهربائية التي تشمل ( الغسالات الفول والفوق والهاف أوتوماتيك – الثلاجات بجميع مقاساتها وأحجامها – الميكروييف وحل لمشاكله المعقده – البوتاجازات الديجيتال والعادية – السخانات الغاز والكهرباء – المرواح بمختلف ماركاتها – الأفران الكهربائية – التكييفات تركيبات وصيانة – أجهزة المطبخ العصري من الخلاطات و الكيتشن ماشيين – الأجهزة المنزلية الصغيرة وماكينات الحلاقة وقص الشعر )  وأيضا في مجال صيانة الشاشات LED – LCD – SMART - والتيلفيزيون CRT    ( توشيبا العربي –  سامسونج – ال جي - يونيون إير – يونيون تيك – جاك – عطا – جولدي - إلخ ) ( TOSHIBA – SAMSUNG – LG – UNIONAIRE – SHARP – UNIONTECH – JAC –ATA – GOLDI – ETC) أيضا المركز متخصص في أقوي منظومة قطع غيار أصلية للأجهزة منزلية وشاشات وتيليفزيون بالمنوفية  قطع غيار غسالات فول وفوق وهاف اوتوماتيك – ثلاجات – مراوح – تكييفات – شاشات – تيليفزيون CRT .)")
                .addGooglePlayStoreLink("https://play.google.com/store/apps/developer?id=semicolonsoft")
                .addFacebookLink("https://www.facebook.com/%D8%A7%D9%84%D8%A5%D9%8A%D9%85%D8%A7%D9%86-%D9%84%D9%84%D8%B5%D9%8A%D8%A7%D9%86%D8%A9-%D8%A7%D9%84%D9%85%D8%B9%D8%AA%D9%85%D8%AF%D8%A9-1727801847518868/")
                .addTwitterLink("https://twitter.com/")
                .addInstagramLink("https://www.instagram.com/")
                .addGooglePlusLink("https://plus.google.com/u/0/116456341637295042875")
                .addEmailLink("info@semicolonsoft.com")
                .addWhatsappLink("مركز الايمان", "+201062737363")
                .addWebsiteLink("https://www.semicolonsoft.com")
                .addFiveStarsAction("https://play.google.com/store/apps/developer?id=semicolonsoft")
                .addPrivacyPolicyAction("www.semicolonsoft.com")
                .addMoreFromMeAction("https://play.google.com/store/apps/developer?id=semicolonsoft")
                .setVersionNameAsAppSubTitle()
                .setActionsColumnsCount(2)
                .addFeedbackAction("info@semicolonsoft.com")
                .setWrapScrollView(true)
                .setShowAsCard(true);


        AboutView view = builder.build();

        flHolder.addView(view);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(About.this,Category.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
