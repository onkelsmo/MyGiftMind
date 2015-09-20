package mediasmo.mygiftmind;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;

import mediasmo.mygiftmind.Fragments.TabFragment;
import mediasmo.mygiftmind.dao.Contact;
import mediasmo.mygiftmind.helper.DatabaseHandler;

/**
 * MainActivity
 */
public class MainActivity extends AppCompatActivity {
    private DrawerLayout menuDrawerLayout;
    private CharSequence menuTitle;
    private DatabaseHandler db;
    private SimpleCursorAdapter dataAdapter;
    private NavigationView menuNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    /**
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuNavigationView = (NavigationView) findViewById(R.id.left_drawer);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new TabFragment());

        menuNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_item_contacts) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, new TabFragment()).commit();
                }
                /*if (menuItem.getItemId() == R.id.nav_item_settings) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, new SettingsFragment()).commit();
                }*/
                return false;
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                menuDrawerLayout,
                toolbar,
                R.string.app_name,
                R.string.app_name);

        menuDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();


        // Enable ActionBar app icon to behave as action to toggle nav drawer
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/



        db = new DatabaseHandler(this);
        // Display Contacts from Database
        //displayContacts();
    }

    /**
     * displayContacts
     */
    private void displayContacts() {
        Cursor cursor = db.getAllContacts();
        String[] columns = new String[] {
                DatabaseHandler.KEY_NAME,
                DatabaseHandler.KEY_ID
        };
        int[] to = new int[] {
                R.id.name,
                R.id.key,
        };
        dataAdapter = new SimpleCursorAdapter(
              this, R.layout.contact_list_item,
                cursor,
                columns,
                to,
                0
        );

        final ListView listView = (ListView)findViewById(R.id.contact_list);
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                // @TODO: take data and go to activity to show detailed data and give possibility to delete and modify data
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                Contact contact = new Contact(id, name);
                openContactDetailsActivity(contact);
            }
        });
    }

    /**
     * openContactDetailsActivity
     *
     * @param contact Contact
     */
    private void openContactDetailsActivity(Contact contact) {
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtra("ContactObject", contact);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
