import java.util.*;

// Entidade Veículo
class Veículo {
    private String placa;
    private String modelo;
    private String cor;

    public Veículo(String placa, String modelo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    
    public String toString() {
        return "Veículo [placa=" + placa + ", modelo=" + modelo + ", cor=" + cor + "]";
    }
}

// Entidade Cliente
class Cliente {
    private String nome;
    private String telefone;

    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    
    public String toString() {
        return "Cliente [nome=" + nome + ", telefone=" + telefone + "]";
    }
}

// Entidade Pátio
class Pátio {
    private String nome;
    private List<Veículo> veículos;

    public Pátio(String nome) {
        this.nome = nome;
        this.veículos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Veículo> getVeículos() {
        return veículos;
    }

    public void adicionarVeículo(Veículo veículo) {
        veículos.add(veículo);
    }

    public void removerVeículo(Veículo veículo) {
        veículos.remove(veículo);
    }

    
    public String toString() {
        return "Pátio [nome=" + nome + ", veículos=" + veículos + "]";
    }
}

// Repositório de Veículos
class RepositórioDeVeículos {
    private Map<String, Veículo> veículos = new HashMap<>();

    public void adicionarVeículo(Veículo veículo) {
        veículos.put(veículo.getPlaca(), veículo);
    }

    public Veículo buscarVeículo(String placa) {
        return veículos.get(placa);
    }

    public void removerVeículo(String placa) {
        veículos.remove(placa);
    }
}

// Repositório de Clientes
class RepositórioDeClientes {
    private Map<String, Cliente> clientes = new HashMap<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getNome(), cliente);
    }

    public Cliente buscarCliente(String nome) {
        return clientes.get(nome);
    }

    public void removerCliente(String nome) {
        clientes.remove(nome);
    }
}

// Serviço de Estacionamento
class ServiçoDeEstacionamento {
    private RepositórioDeVeículos repositórioDeVeículos;
    private Pátio pátio;

    public ServiçoDeEstacionamento(RepositórioDeVeículos repositórioDeVeículos, Pátio pátio) {
        this.repositórioDeVeículos = repositórioDeVeículos;
        this.pátio = pátio;
    }

    public void estacionarVeículo(Veículo veículo) {
        repositórioDeVeículos.adicionarVeículo(veículo);
        pátio.adicionarVeículo(veículo);
        System.out.println("Veículo estacionado: " + veículo);
    }

    public void retirarVeículo(String placa) {
        Veículo veículo = repositórioDeVeículos.buscarVeículo(placa);
        if (veículo != null) {
            pátio.removerVeículo(veículo);
            repositórioDeVeículos.removerVeículo(placa);
            System.out.println("Veículo retirado: " + veículo);
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }
}

// Serviço de Monitoramento
class ServiçoDeMonitoramento {
    private Pátio pátio;

    public ServiçoDeMonitoramento(Pátio pátio) {
        this.pátio = pátio;
    }

    public void listarVeículos() {
        System.out.println("Veículos no pátio " + pátio.getNome() + ":");
        for (Veículo veículo : pátio.getVeículos()) {
            System.out.println(veículo);
        }
    }
}

// Classe principal para testar o sistema
public class SistemaDeControleDePátio {
    public static void main(String[] args) {
        // Criando repositórios
        RepositórioDeVeículos repositórioDeVeículos = new RepositórioDeVeículos();
        RepositórioDeClientes repositórioDeClientes = new RepositórioDeClientes();

        // Criando um pátio
        Pátio pátio = new Pátio("Pátio Central");

        // Criando serviços
        ServiçoDeEstacionamento serviçoDeEstacionamento = new ServiçoDeEstacionamento(repositórioDeVeículos, pátio);
        ServiçoDeMonitoramento serviçoDeMonitoramento = new ServiçoDeMonitoramento(pátio);

        // Criando cliente
        Cliente cliente1 = new Cliente("João", "1234-5678");
        repositórioDeClientes.adicionarCliente(cliente1);

        // Criando veículo
        Veículo veículo1 = new Veículo("ABC-1234", "Toyota Corolla", "Prata");

        // Estacionando o veículo
        serviçoDeEstacionamento.estacionarVeículo(veículo1);

        // Monitorando o pátio
        serviçoDeMonitoramento.listarVeículos();

        // Retirando o veículo
        serviçoDeEstacionamento.retirarVeículo("ABC-1234");

        // Monitorando novamente o pátio
        serviçoDeMonitoramento.listarVeículos();
    }
}
