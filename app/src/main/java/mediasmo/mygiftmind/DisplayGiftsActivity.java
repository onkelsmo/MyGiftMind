package mediasmo.mygiftmind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class DisplayGiftsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_gifts);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_contacts:
                openContacts();
                return true;
            case R.id.action_gifts:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openContacts() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
