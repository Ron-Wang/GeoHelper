package com.thutyh.geology_helper11;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.tan;

public class DateCalActivity extends AppCompatActivity {

    final Context context = this;
    private Button btnX;
    private TextView txtXW;
    float diaJ1,dipJ1,phaJ1,cJ1,
            diaJ2,dipJ2,phaJ2,cJ2,
            diaJ3,dipJ3,
            diaJ4,dipJ4,
            diaJ5,dipJ5,length,gamma,gammawater,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_cal);

        btnX = (Button)findViewById(R.id.btnXie);
        txtXW = (TextView)findViewById(R.id.TxtXieW);

        btnX.setOnClickListener(btnXOnclick);
    }

    private View.OnClickListener btnXOnclick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.dialog_xie, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptsView);

            final EditText text_dipJ1 = (EditText) promptsView.findViewById(R.id.text_dipJ1),
                    text_diaJ1 = (EditText) promptsView.findViewById(R.id.text_diaJ1),
                    text_phaJ1 = (EditText) promptsView.findViewById(R.id.text_phaJ1),
                    text_cJ1 = (EditText) promptsView.findViewById(R.id.text_cJ1),
                    text_dipJ2 = (EditText) promptsView.findViewById(R.id.text_dipJ2),
                    text_diaJ2 = (EditText) promptsView.findViewById(R.id.text_diaJ2),
                    text_phaJ2 = (EditText) promptsView.findViewById(R.id.text_phaJ2),
                    text_cJ2 = (EditText) promptsView.findViewById(R.id.text_cJ2),
                    text_dipJ3 = (EditText) promptsView.findViewById(R.id.text_dipJ3),
                    text_diaJ3 = (EditText) promptsView.findViewById(R.id.text_diaJ3),
                    text_dipJ4 = (EditText) promptsView.findViewById(R.id.text_dipJ4),
                    text_diaJ4 = (EditText) promptsView.findViewById(R.id.text_diaJ4),
                    text_dipJ5 = (EditText) promptsView.findViewById(R.id.text_dipJ5),
                    text_diaJ5 = (EditText) promptsView.findViewById(R.id.text_diaJ5),
                    text_length= (EditText) promptsView.findViewById(R.id.text_length),
                    text_gamma= (EditText) promptsView.findViewById(R.id.text_gamma),
                    text_gammawater= (EditText) promptsView.findViewById(R.id.text_gammawater),
                    text_height= (EditText) promptsView.findViewById(R.id.text_height);

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dipJ1 = (float) (Float.valueOf(text_dipJ1.getText().toString()) *Math.PI /180.0);
                                    diaJ1 = (float) (Float.valueOf(text_diaJ1.getText().toString()) *Math.PI /180.0);
                                    phaJ1 = (float) (Float.valueOf(text_phaJ1.getText().toString()) *Math.PI /180.0);
                                    cJ1 = Float.valueOf(text_cJ1.getText().toString());
                                    dipJ2 = (float) (Float.valueOf(text_dipJ2.getText().toString()) *Math.PI /180.0);
                                    diaJ2 = (float) (Float.valueOf(text_diaJ2.getText().toString()) *Math.PI /180.0);
                                    phaJ2 = (float) (Float.valueOf(text_phaJ2.getText().toString()) *Math.PI /180.0);
                                    cJ2 = Float.valueOf(text_cJ2.getText().toString());
                                    dipJ3 = (float) (Float.valueOf(text_dipJ3.getText().toString()) *Math.PI /180.0);
                                    diaJ3 = (float) (Float.valueOf(text_diaJ3.getText().toString()) *Math.PI /180.0);
                                    dipJ4 = (float) (Float.valueOf(text_dipJ4.getText().toString()) *Math.PI /180.0);
                                    diaJ4 = (float) (Float.valueOf(text_diaJ4.getText().toString()) *Math.PI /180.0);
                                    dipJ5 = (float) (Float.valueOf(text_dipJ5.getText().toString()) *Math.PI /180.0);
                                    diaJ5 = (float) (Float.valueOf(text_diaJ5.getText().toString()) *Math.PI /180.0);
                                    length = Float.valueOf(text_length.getText().toString());
                                    gamma = Float.valueOf(text_gamma.getText().toString()) /1000;
                                    gammawater = Float.valueOf(text_gammawater.getText().toString()) /1000;
                                    height = Float.valueOf(text_height.getText().toString());
                                    
                                    double ax,ay,az,bx,by,bz,dx,dy,dz,fx,fy,fz,f5x,f5y,f5z,
                                            gx,gy,gz,g5x,g5y,g5z,ix,iy,iz,jx,jy,jz,j5x,j5y,j5z,kz,lz,
                                            m,m5,n,n5,p,q,q5,r,s5,v5,w5,lamda,lamda5,R,epsilon,rho,mu,upsilon,
                                            G,G5,M,M5,h,h5,A1,A2,A5,W,u1,u2,u5,V,N1,N2,S,Q,FSW,FSD,eta;
                                    eta = 1.0;

                                    ax = Math.sin(dipJ1) * Math.sin(diaJ1);
                                    ay = Math.sin(dipJ1) * Math.cos(diaJ1);
                                    az = Math.cos(dipJ1);
                                    bx = Math.sin(dipJ2) * Math.sin(diaJ2);
                                    by = Math.sin(dipJ2) * Math.cos(diaJ2);
                                    bz = Math.cos(dipJ2);
                                    dx = Math.sin(dipJ4) * Math.sin(diaJ4);
                                    dy = Math.sin(dipJ4) * Math.cos(diaJ4);
                                    dz = Math.cos(dipJ4);
                                    fx = Math.sin(dipJ3) * Math.sin(diaJ3);
                                    fy = Math.sin(dipJ3) * Math.cos(diaJ3);
                                    fz = Math.cos(dipJ3);
                                    f5x = Math.sin(dipJ5) * Math.sin(diaJ5);
                                    f5y = Math.sin(dipJ5) * Math.cos(diaJ5);
                                    f5z = Math.cos(dipJ5);

                                    gx = fy * az - fz * ay;
                                    gy = fz * ax - fx * az;
                                    gz = fx * ay - fy * ax;
                                    g5x = f5y * az - f5z * ay;
                                    g5y = f5z * ax - f5x * az;
                                    g5z = f5x * ay - f5y * ax;
                                    ix = by * az - bz * ay;
                                    iy = bz * ax - bx * az;
                                    iz = bx * ay - by * ax;
                                    jx = fy * dz - fz * dy;
                                    jy = fz * dx - fx * dz;
                                    jz = fx * dy - fy * dx;
                                    j5x = f5y * dz - f5z * dy;
                                    j5y = f5z * dx - f5x * dz;
                                    j5z = f5x * dy - f5y * dx;
                                    kz = ix * by - iy * bx;
                                    lz = ax * iy - ay * ix;

                                    m = gx * dx + gy * dy + gz * dz;
                                    m5 = g5x * dx + g5y * dy + g5z * dz;
                                    n = bx * jx + by * jy + bz * jz;
                                    n5 = bx * j5x + by * j5y + bz * j5z;
                                    p = ix * dx + iy * dy + iz * dz;
                                    q = bx * gx + by * gy + bz * gz;
                                    q5 = bx * g5x + by * g5y + bz * g5z;
                                    r = ax * bx + ay * by + az * bz;
                                    s5 = ax * f5x + ay * f5y + az * f5z;
                                    v5 = bx * f5x + by * f5y + bz * f5z;
                                    w5 = ix * f5x + iy * f5y + iz * f5z;
                                    lamda = ix * gx + iy * gy + iz * gz;
                                    lamda5 = ix * g5x + iy * g5y + iz * g5z;
                                    epsilon = fx * f5x + fy * f5y + fz * f5z;

                                    R = Math.sqrt(1 - r * r);
                                    rho = n * q / (R *R * Math.abs(n * q));
                                    mu = m * q / (R *R * Math.abs(m * q));
                                    upsilon = p / (R * Math.abs(p));
                                    G = gx *gx + gy *gy + gz *gz;
                                    G5 = g5x *g5x + g5y *g5y + g5z *g5z;
                                    M = Math.sqrt(G * p *p - 2 * m * p * lamda + m *m * R *R);
                                    M5 = Math.sqrt (G5 * p *p - 2 * m5 * p * lamda5 + m5 *m5 * R *R);
                                    h = height * 3.280840 / Math.abs(gz);
                                    h5 = (M * h - Math.abs(p) * length * 3.280840) / M5;
                                    
                                    A1 = (Math.abs(m * q) * h *h - Math.abs(m5 * q5) * h5 *h5) / (2 * Math.abs(p));
                                    A2 = (Math.abs(q / n) * m *m * h *h - Math.abs(q5 / n5) * m5 *m5 * h5 *h5) / (2 * Math.abs(p));
                                    A5 = Math.abs(m5 * q5) * h5 *h5 / (2 * Math.abs(n5));
                                    W = gamma * 62.50* (q *q * m *m *h *h *h / Math.abs(n) - q5 *q5 * m5 *m5 * h5 *h5 *h5 / Math.abs(n5)) / (6 * Math.abs(p));
                                    
                                    u1 = u2 = u5 = gammawater * 62.50* h5 * Math.abs(m5) / (3 * dz);
                                    V = u5 * A5 * eta * epsilon / (Math.abs(epsilon));
                                    N1 = rho * (W * kz + V * (r * v5 - s5)) - u1 * A1;
                                    N2 = mu * (W * lz + V * (r * s5 - v5)) - u2 * A2;
                                    S = upsilon * (W * iz - V * w5);
                                    Q = N1 *tan(phaJ1) + N2 * tan(phaJ2) + cJ1 * 20.920502* A1 + cJ2 * 20.920502* A2;
                                    FSW = Q / S;

                                    u1 = u2 = u5 = 0;
                                    V = u5 * A5 * eta * epsilon / (Math.abs(epsilon));
                                    N1 = rho * (W * kz + V * (r * v5 - s5)) - u1 * A1;
                                    N2 = mu * (W * lz + V * (r * s5 - v5)) - u2 * A2;
                                    S = upsilon * (W * iz - V * w5);
                                    Q = N1 *tan(phaJ1) + N2 * tan(phaJ2) + cJ1 * 20.920502* A1 + cJ2 * 20.920502* A2;
                                    FSD = Q / S;
                                    txtXW.setText("充水：\n" + String.valueOf(FSW) + "\n" + "干燥：\n" + String.valueOf(FSD));

                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();        }
    };
}
