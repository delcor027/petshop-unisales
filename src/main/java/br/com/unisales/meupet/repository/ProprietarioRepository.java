package br.com.unisales.meupet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import br.com.unisales.meupet.table.Proprietario;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Integer> {
    List<Proprietario> findByAtivo(Byte ativo);
}

