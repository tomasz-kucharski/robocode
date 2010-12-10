package robotgame.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Toast;
import robotgame.android.loader.AndroidTextureLoader;

/**
 * @author tomekk
 * @since 2010-10-17, 00:04:01
 */
public class RobotGameActivity extends Activity {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.evolve :
                view.getConfiguration().setEvolve(!view.getConfiguration().isEvolve());
                break;
            case R.id.light :
                view.getConfiguration().changeLightEnabled();
                break;
            case R.id.menuPreferences :
                startActivity(new Intent(this,Preferences.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
