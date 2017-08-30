package com.example.elashry.eleman.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.elashry.eleman.R;
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
                .setPhoto(R.drawable.semi)
                .setCover(R.drawable.pic)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("مركز الايمان للصيانة ")
                .setSubTitle("نبذة عن مركز الإيمان للصيانة المعتمدة بالبتانون ")
                .setLinksColumnsCount(4)
                .setBrief("   مركز الإيمان للصيانة المعتمدة وكلاء صيانة معتمدون ومتخصصون في مجال صيانة الأجهزة المنزلية والكهربائية التي تشمل ( الغسالات الفول والفوق والهاف أوتوماتيك – الثلاجات بجميع مقاساتها وأحجامها – الميكروييف وحل لمشاكله المعقده – البوتاجازات الديجيتال والعادية – السخانات الغاز والكهرباء – المرواح بمختلف ماركاتها – الأفران الكهربائية – التكييفات تركيبات وصيانة – أجهزة المطبخ العصري من الخلاطات و الكيتشن ماشيين – الأجهزة المنزلية الصغيرة وماكينات الحلاقة وقص الشعر ) \n" +
                        "وأيضا في مجال صيانة الشاشات LED – LCD – SMART - \tوالتيلفيزيون CRT  \n" +
                        "( توشيبا العربي –  سامسونج – ال جي - يونيون إير – يونيون تيك – جاك – عطا – جولدي - إلخ ) \n" +
                        "( TOSHIBA – SAMSUNG – LG – UNIONAIRE – SHARP – UNIONTECH – JAC –ATA – GOLDI – ETC)\n" +
                        "أيضا المركز متخصص في أقوي منظومة قطع غيار أصلية للأجهزة منزلية وشاشات وتيليفزيون بالمنوفية \n" +
                        "قطع غيار غسالات فول وفوق وهاف اوتوماتيك – ثلاجات – مراوح – تكييفات – شاشات – تيليفزيون CRT \n.")
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("semicolon")
                .addBitbucketLink("Melashry")
                .addFacebookLink("https://www.facebook.com/%D8%A7%D9%84%D8%A5%D9%8A%D9%85%D8%A7%D9%86-%D9%84%D9%84%D8%B5%D9%8A%D8%A7%D9%86%D8%A9-%D8%A7%D9%84%D9%85%D8%B9%D8%AA%D9%85%D8%AF%D8%A9-1727801847518868")
                .addTwitterLink("Melashry")
                .addInstagramLink("semicolon")
                .addGooglePlusLink("semicolon")
                .addYoutubeChannelLink("https://www.semicolonsoft.com")
                .addDribbbleLink("https://www.semicolonsoft.com")
                .addLinkedInLink("https://www.semicolonsoft.com")
                .addEmailLink("https://www.semicolonsoft.com")
                .addWhatsappLink("مركز الايمان", "01062737363")
                .addSkypeLink("https://www.semicolonsoft.com")
                .addGoogleLink("https://www.semicolonsoft.com")
                .addAndroidLink("https://www.semicolonsoft.com")
                .addWebsiteLink("https://www.semicolonsoft.com")
                .addFiveStarsAction()
                .addMoreFromMeAction("https://www.semicolonsoft.com")
                .setVersionNameAsAppSubTitle()
                .addShareAction("https://www.semicolonsoft.com")
                .addUpdateAction()
                .setActionsColumnsCount(2)
                .addFeedbackAction("vwww.semicolonsoft.com")
                .addPrivacyPolicyAction("www.semicolonsoft.com")
                .addIntroduceAction((Intent) null)
                .addHelpAction((Intent) null)
                .addChangeLogAction((Intent) null)
                .addRemoveAdsAction((Intent) null)
                .addDonateAction((Intent) null)
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
