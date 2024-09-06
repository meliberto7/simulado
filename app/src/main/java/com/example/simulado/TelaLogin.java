package com.example.simulado;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.simulado.helper.ModelDAO;
import com.example.simulado.model.Global;

import java.util.concurrent.Executor;

public class TelaLogin extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button buttonEntrar;
    private int cont = 0;
    private boolean very = false;
    private BiometricPrompt biometricPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        buttonEntrar = findViewById(R.id.buttonEntrar);



        if (Global.isLogado()) {

            BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Validação Biometrica")
                    .setDescription("Confirme a autenticação no aplicativo")
                    .setNegativeButtonText("Cancelar")
                    .build();

            Executor executor = ContextCompat.getMainExecutor(TelaLogin.this);

            biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(TelaLogin.this, "Error", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(TelaLogin.this, "Sucesso", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    cont++;

                    if (cont == 3) {

                        biometricPrompt.cancelAuthentication();

                    }

                    Toast.makeText(TelaLogin.this, "Falhou", Toast.LENGTH_SHORT).show();
                }
            });

            biometricPrompt.authenticate(promptInfo);

        }

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();

                ModelDAO dao = new ModelDAO(TelaLogin.this);

                if (dao.logar(email, senha)) {

                    Global.setLogado(true);

                    Intent intent = new Intent(TelaLogin.this, TelaMaterias.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(TelaLogin.this, "Falha login", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}