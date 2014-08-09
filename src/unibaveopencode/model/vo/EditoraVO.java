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
@EqualsAndHashCode(of = "codEditora") //Cria o HashCode a partir do ID
@Entity //Cria uma entidade Hibernate
@Table(name = "editora") //Relaciona a VO com a tabela no banco de dados
@SequenceGenerator(name = "ID_EDITORA", sequenceName = "seq_id_editora")
@NamedQueries({
    @NamedQuery(name = "EditoraVO.findAll", query = "SELECT a FROM EditoraVO a"), //Encontra todos os dados da tabela
    @NamedQuery(name = "EditoraVO.findCodEditora", query = "SELECT a FROM EditoraVO a WHERE a.codEditora = :codEditora"), //Encontra as editoras por código
    @NamedQuery(name = "EditoraVO.findNomEditora", query = "SELECT a FROM EditoraVO a WHERE UPPER(a.nomEditora) LIKE '%'||UPPER(:nomEditora)||'%'") //Encontra as editoras por nome
})
public class EditoraVO implements Serializable {

    public static final String FIND_ALL = "EditoraVO.findAll";
    public static final String FIND_COD_EDITORA = "EditoraVO.findCodEditora";
    public static final String FIND_NOME_EDITORA = "EditoraVO.findNomEditora";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_EDITORA")
    @Column(name = "cod_editora")
    private Integer codEditora;

    @Column(name = "nom_editora")
    private String nomEditora;

}
