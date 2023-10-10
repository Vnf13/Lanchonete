package br.com.viniciusNascimento.lanchonete.api.mapper;

import br.com.viniciusNascimento.lanchonete.api.model.ClienteModel;
import br.com.viniciusNascimento.lanchonete.api.model.input.ClienteInputModel;
import br.com.viniciusNascimento.lanchonete.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteMapper {

    private ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }
    public Cliente toEntity(ClienteInputModel clienteInputModel){
       return modelMapper.map(clienteInputModel, Cliente.class);
    }
    public List<ClienteModel> toCollectionModel(List<Cliente> clientes){
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
