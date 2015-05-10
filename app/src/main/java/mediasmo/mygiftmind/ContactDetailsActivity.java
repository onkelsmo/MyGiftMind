package mediasmo.mygiftmind;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import mediasmo.mygiftmind.dao.Contact;

public class ContactDetailsActivity extends ActionBarActivity {
    private String[] menuTitles;
    private DrawerLayout menuDrawerLayout;
    private ListView menuDrawerList;
    private ActionBarDrawerToggle menuDrawerToggle;
    private CharSequence menuDrawerTitle;
    private CharSequence menuTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        menuTitle = menuDrawerTitle = getTitle();
        menuTitles = getResources().getStringArray(R.array.menu_titles);
        menuDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        menuDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_list_item, menuTitles));
        // Set the list's click listener
        menuDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        menuDrawerToggle = new ActionBarDrawerToggle(
                this,
                menuDrawerLayout,
                R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(menuTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(menuDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        menuDrawerLayout.setDrawerListener(menuDrawerToggle);
        Contact contact = (Contact)getIntent().getSerializableExtra("ContactObject");

        TextView contactNameTextView = (TextView)findViewById(R.id.contact_name);
        contactNameTextView.setText(contact.getName());

        //TextView contactIdTextView = (TextView)findViewById(R.id.contact_id);
        //contactIdTextView.setText(contact.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (menuDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.action_contacts:
                openContacts();
                return true;
            case R.id.action_gifts:
                openGifts();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openContacts() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openGifts() {
        Intent intent = new Intent(this, DisplayGiftsActivity.class);
        startActivity(intent);
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        /**
         * position can be:
         * 0 = add
         * 1 = mod
         * 2 = del
         */
        switch (position) {
            case 0:
                // @TODO: change that gift can be added to the given customer here
                Intent addIntent = new Intent(this, AddContactActivity.class);
                startActivity(addIntent);
                break;
            case 1:
                Intent modIntent = new Intent(this, ModContactActivity.class);
                startActivity(modIntent);
                break;
            case 2:
                Intent delIntent = new Intent(this, DelContactActivity.class);
                startActivity(delIntent);
                break;
            default:
                break;
        }

        // Update selected item and title, then close the drawer
        menuDrawerList.setItemChecked(position, true);
        //setTitle(menuTitles[position]);
        menuDrawerLayout.closeDrawer(menuDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        menuTitle = title;
        getSupportActionBar().setTitle(menuTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        menuDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        menuDrawerToggle.onConfigurationChanged(newConfig);
    }
}
