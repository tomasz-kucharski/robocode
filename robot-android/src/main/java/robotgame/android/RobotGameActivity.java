package robotgame.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import robotgame.android.loader.TextureLoader;

import java.net.MalformedURLException;

/**
 * @author tomekk
 * @since 2010-10-17, 00:04:01
 */
public class RobotGameActivity extends Activity implements OnClickListener {

    private RobotGameView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

//        this.setContentView(R.layout.main);
//        Button button = (Button) this.findViewById(R.id.button_device);
//        button.setOnClickListener(this);
        
        view = new RobotGameView(this);
        setContentView(view);
    }

    @Override
    public void onClick(View view) {

        TextureLoader loader = new TextureLoader();
        loader.setResources(this.getResources());
        loader.loadTextures();

        Toast toast = Toast.makeText(this, "RobtoGame PoC", Toast.LENGTH_SHORT);
        toast.show();
    }
}
