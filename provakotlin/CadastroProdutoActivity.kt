import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aula14.R

class CadastroProdutoActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
        private lateinit var etCategoria: EditText
            private lateinit var etPreco: EditText
                private lateinit var etQuantidade: EditText
                    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        etNome = findViewById(R.id.etNome)
            etCategoria = findViewById(R.id.etCategoria)
                etPreco = findViewById(R.id.etPreco)
                    etQuantidade = findViewById(R.id.etQuantidade)
                        btnCadastrar = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            val nome = etNome.text.toString().trim()
                val categoria = etCategoria.text.toString().trim()
                    val precoText = etPreco.text.toString().trim()
                        val quantidadeText = etQuantidade.text.toString().trim()

            if (nome.isEmpty() || categoria.isEmpty() || precoText.isEmpty() || quantidadeText.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val preco = precoText.toDoubleOrNull()
            val quantidade = quantidadeText.toIntOrNull()

            if (preco == null || quantidade == null) {
                Toast.makeText(this, "Preço e Quantidade devem ser preenchidos em formato numérico.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
            }
            if (preco < 0) {
                Toast.makeText(this, "O preço não deve ser negativo ou menor que 0.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
            }
            if (quantidade < 1) {
                Toast.makeText(this, "Quantidadem minima de 1.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
            }

            val produto = Produto(nome, categoria, preco, quantidade)
                Estoque.adicionarProduto(produto)

            Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

            etNome.text.clear()
                etCategoria.text.clear()
                    etPreco.text.clear()
                        etQuantidade.text.clear()
        }
    }
}
