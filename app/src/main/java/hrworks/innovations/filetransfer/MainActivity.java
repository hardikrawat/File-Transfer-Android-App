package hrworks.innovations.filetransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import hrworks.innovations.filetransfer.utils.Client;
import hrworks.innovations.filetransfer.utils.Server;

public class MainActivity extends AppCompatActivity {

    private Button btnSend, btnReceive;
    private Client client; //sender
    private Server server; //receiver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (initViews()) System.out.println("[INFO] views initialized"); else return;

        btnSend.setOnClickListener(view -> {
            try {
                client.execute(getIp());
            } catch (Exception e){
                System.out.println(e);
            }
        });

        btnReceive.setOnClickListener(view -> {
            server.execute();
        });
    }

    public Boolean initViews(){
        try {
            btnSend = this.findViewById(R.id.sendBtn);
            btnReceive = this.findViewById(R.id.receiveBtn);
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }


    public String getIp(){
        Context context = this.getApplicationContext();
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }

}