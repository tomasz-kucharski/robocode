package robotgame.android;

import android.app.Activity;
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
		if (item.getItemId() == R.id.evolve) {
			view.getConfiguration().setEvolve(!view.getConfiguration().isEvolve());
			return true;
		} else if (item.getItemId() == R.id.light) {
                view.getConfiguration().changeLightEnabled();
                return true;
            }
		return super.onOptionsItemSelected(item);
	}
}
