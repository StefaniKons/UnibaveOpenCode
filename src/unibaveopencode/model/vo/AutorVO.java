/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.model.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

/**
 *
 * @author Stéfani
 */
@Data //Cria os get e set
@Builder //Cria um construtor dinâmico
@NoArgsConstructor //Cria um construtor sem argumentos
@AllArgsConstructor //Cria um construtor com todos os argumentos
@EqualsAndHashCode(of = "codAutor") //Cria o HashCode a partir do ID
@Entity //Cria uma entidade Hibernate
@Table(name = "autor") //Relaciona a VO com a tabela no banco de dados
@SequenceGenerator(name = "ID_AUTOR", sequenceName = "seq_id_autor")
@NamedQueries({
    @NamedQuery(name = "AutorVO.findAll", query = "SELECT a FROM AutorVO a"), //Encontra todos os dados da tabela
    @NamedQuery(name = "AutorVO.findCodAutor", query = "SELECT a FROM AutorVO a WHERE a.codAutor = :codAutor"), //Encontra os autores por código
    @NamedQuery(name = "AutorVO.findNomAutor", query = "SELECT a FROM AutorVO a WHERE UPPER(a.nomAutor) LIKE '%'||UPPER(:nomAutor)||'%'") //Encontra os autores por código
})
public class AutorVO implements Serializable {

public static final String FIND_ALL = "AutorVO.findAll"; 
public static final String FIND_COD_AUTOR = "AutorVO.findCodAutor"; 
public static final String FIND_NOME_AUTOR = "AutorVO.findNomAutor"; 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_AUTOR")
    @Column(name = "cod_autor")
    private Integer codAutor;

    @Column(name = "nom_autor")
    private String nomAutor;

}
