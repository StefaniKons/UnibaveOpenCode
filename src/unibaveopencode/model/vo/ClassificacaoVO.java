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
@EqualsAndHashCode(of = "desClassificacao") //Cria o HashCode a partir do ID
@Entity //Cria uma entidade Hibernate
@Table(name = "classificacao") //Relaciona a VO com a tabela no banco de dados
@SequenceGenerator(name = "ID_CLASSIFICACAO", sequenceName = "seq_id_classificacao")
@NamedQueries({
    @NamedQuery(name = "ClassificacaoVO.findAll", query = "SELECT a FROM ClassificacaoVO a"), //Encontra todos os dados da tabela
    @NamedQuery(name = "ClassificacaoVO.findCodClassificacao", query = "SELECT a FROM ClassificacaoVO a WHERE a.codClassificacao = :codClassificacao"), //Encontra as classificações por código
    @NamedQuery(name = "ClassificacaoVO.findDesClassificacao", query = "SELECT a FROM ClassificacaoVO a WHERE UPPER(a.desClassificacao) LIKE '%'||UPPER(:desClassificacao)||'%'") //Encontra as classificações por nome
})
public class ClassificacaoVO implements Serializable{
    
    public static final String FIND_ALL = "ClassificacaoVO.findAll";
    public static final String FIND_COD_CLASSIFICACAO = "ClassificacaoVO.findCodClassificacao";
    public static final String FIND_DESCRICAO_CLASSIFICACAO = "ClassificacaoVO.findDesClassificacao";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_CLASSIFICACAO")
    @Column (name = "cod_classificacao")
    private Integer codClassificacao;
            
    @Column(name = "des_classificacao")
    private String desClassificacao;
        
}
