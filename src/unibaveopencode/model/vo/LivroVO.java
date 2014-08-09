/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.model.vo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@EqualsAndHashCode(of = {"numTombo"}) //Cria o HashCode a partir do ID
@Entity //Cria uma entidade Hibernate
@Table(name = "livro") //Relaciona a VO com a tabela no banco de dados
@NamedQueries({
    @NamedQuery(name = "LivroVO.findAll", query = "SELECT a FROM LivroVO a"), //Encontra todos os dados da tabela
    @NamedQuery(name = "LivroVO.findCodLivro", query = "SELECT a FROM LivroVO a WHERE a.codLivro = :codLivro"), //Encontra os livros por código
    @NamedQuery(name = "LivroVO.findNomLivro", query = "SELECT a FROM LivroVO a WHERE UPPER(a.nomLivro) LIKE '%'||UPPER(:nomLivro)||'%'") //Encontra os livros por nome
})
public class LivroVO implements Serializable {

    @Id
    @Column(name = "num_tombo", nullable = false)
    private Integer numTombo;

    @Column(name = "nom_titulo")
    private String nomTitulo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_autor")
    private List<AutorVO> autor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_editora")
    private EditoraVO editora;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_classificacao")
    private ClassificacaoVO classificacao;

    @Column(name = "des_ano")
    private int desAno;

    @Column(name = "des_url")
    private String desUrl;
}
