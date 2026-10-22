package br.com.fiap.portal.controller;

import br.com.fiap.portal.dto.AlunoRequestDTO;
import br.com.fiap.portal.dto.AlunoResponseDTO;
import br.com.fiap.portal.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@Tag(
        name = "Alunos",
        description = "Operações de gerenciamento de alunos"
)

public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar aluno")
    public ResponseEntity<AlunoResponseDTO> criar(@Valid @RequestBody AlunoRequestDTO aluno){
        AlunoResponseDTO resposta = alunoService.criar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
    @GetMapping
    @Operation(
            summary = "Listar alunos",
            description = "Retorna todos os alunos cadastrados."
    )

    public List<AlunoResponseDTO> listar(){
        return alunoService.listar();
    }
    @GetMapping("/{id}")
    @Operation (summary = "Buscar Aluno por ID")
    public AlunoResponseDTO buscarPorId(
            @Parameter(description="Identificador do Aluno",
                    example= "1", required =true) @PathVariable Long id){
        return alunoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno")
    public AlunoResponseDTO atualizar(@PathVariable Long id,
                           @Valid @RequestBody AlunoRequestDTO aluno){
        return alunoService.atualizar(id, aluno);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir aluno")
    public void excluir (@PathVariable Long id){
        alunoService.excluir(id);
    }

    @GetMapping("/teste")
    public String teste(){
        return "Server On!";
    }

}
