package br.com.fiap.portal.service;

import br.com.fiap.portal.dto.AlunoRequestDTO;
import br.com.fiap.portal.dto.AlunoResponseDTO;
import br.com.fiap.portal.model.Aluno;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.fiap.portal.repository.AlunoRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponseDTO criar(AlunoRequestDTO dados) {
        Aluno aluno = new Aluno();

        aluno.setNome(dados.nome());
        aluno.setEmail(dados.email());
        aluno.setTelefone(dados.telefone());


        Aluno salvo = alunoRepository.save(aluno);

        return paraResponseDTO(salvo);
    }

    public AlunoResponseDTO buscarPorId(Long id){
        return paraResponseDTO(buscarEntidadePorId(id));
    }

    public void excluir(Long id){
        alunoRepository.delete(buscarEntidadePorId(id));
    }

    public List<AlunoResponseDTO> listar(){
        return alunoRepository.findAll()
                .stream()
                .map(this::paraResponseDTO)
                .toList();
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dados){
        Aluno aluno = buscarEntidadePorId(id);

        aluno.setNome(dados.nome());
        aluno.setEmail(dados.email());
        aluno.setTelefone(dados.telefone());

        return paraResponseDTO(alunoRepository.save(aluno));
    }

    private Aluno buscarEntidadePorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aluno não encontrado com o ID " + id));
    }

    private AlunoResponseDTO paraResponseDTO(Aluno aluno) {
        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getTelefone()
        );
    }
}
