package docent.namsanhanok;

import android.graphics.Typeface;

import com.github.angads25.toggle.LabeledSwitch;
import com.tsengvn.typekit.Typekit;

import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Application extends android.app.Application {
    public boolean isScanning;
    public boolean isToggleOn;
    LabeledSwitch toggleBtn;

    private static Application instance;
    private static String baseUrl = "http://175.123.138.122:3200";

    private NetworkService networkService;
    public static Application getInstance() {
        return instance;
    }
    public NetworkService getNetworkService() {
        return networkService;
    }

    /**
     * onCreate()
     * 액티비티, 리시버, 서비스가 생성되기전 어플리케이션이 시작 중일때
     * Application onCreate() 메서드가 만들어 진다고 나와 있습니다.
     * by. Developer 사이트
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Application scanState = ((Application) getApplicationContext());
        isScanning = scanState.getScanning();
        isToggleOn = scanState.getToggleState();


//        Typekit.getInstance()
//                .addNormal(Typekit.createFromAsset(this, "NanumMyeongjo.otf"))
//                .addBold(Typekit.createFromAsset(this, "NanumMyeongjoBold.otf"))
//                .addItalic(Typekit.createFromAsset(this, "NanumMyeongjoExtraBold.otf"));
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "SeoulHangangB.ttf"))
                .addBold(Typekit.createFromAsset(this, "SeoulHangangEB.ttf"));

        Application.instance = this;
        buildService();
    }

    public void setScanning(boolean isScanning) {
        this.isScanning = isScanning;
    }

    public boolean getScanning() {
        return isScanning;
    }

    public void setToggleState(boolean isToggleOn) {
        this.isToggleOn = isToggleOn;
    }

    public boolean getToggleState() {
        return isToggleOn;
    }

    public void buildService() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);
    }


}
