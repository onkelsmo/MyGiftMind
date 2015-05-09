package mediasmo.mygiftmind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mediasmo.mygiftmind.dao.Contact;
import mediasmo.mygiftmind.helper.DatabaseHandler;


public class AddContactActivity extends ActionBarActivity {
    private Button buttonSaveContact;
    private EditText editTextContactName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        buttonSaveContact = (Button)findViewById(R.id.buttonSaveContact);
        editTextContactName = (EditText)findViewById(R.id.editTextContactName);

        editTextContactName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        saveInput(view);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveInput(View view) {
        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert: ", "saveInput...");
        if (editTextContactName.getText().toString() != "") {
            db.addContact(new Contact(editTextContactName.getText().toString()));
            Log.v("editTextContactName", editTextContactName.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        // @TODO: Message to user that input field is empty
    }
}
