package utfpr.ct.dainf.if62c.avaliacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */
public class Agenda {
    private final String descricao;
    private final List<Compromisso> compromissos = new ArrayList<>();
    private final Timer timer;

    public Agenda(String descricao) {
        this.descricao = descricao;
        timer = new Timer(descricao);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }
    
    public void novoCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
        Aviso aviso = new AvisoFinal(compromisso);
        compromisso.registraAviso(aviso);
        // com a classe Aviso devidamente implementada, o erro de compilação
        // deverá desaparecer
        timer.schedule(aviso, compromisso.getData());
    }
    
    public void novoAviso(Compromisso compromisso, int antecedencia) {
        Aviso a = new Aviso(compromisso);
        compromisso.registraAviso(a);
        timer.schedule(a, new Date(compromisso.getData().getTime() 
                - antecedencia*1000));
              
        /*compromisso.registraAviso(new Aviso(new Compromisso(new Date(compromisso.getData().getTime()
                - antecedencia*1000), compromisso.getDescricao())));*/
    }
    
    public void novoAviso(Compromisso compromisso, int antecedencia, int intervalo) {
        Aviso a = new Aviso(compromisso);
        compromisso.registraAviso(a);
        timer.scheduleAtFixedRate(a, new Date(compromisso.getData().getTime() 
                - antecedencia*1000), intervalo*1000);
    }
    
    public void cancela(Compromisso compromisso) {
        for(Aviso a : compromisso.getAvisos())
        {
            a.cancel();
        }
        
        compromissos.remove(compromisso);
    }
    
    public void cancela(Aviso aviso) {
        aviso.cancel();
    }
    
    public void destroi() {
        timer.cancel();
        timer.purge();
    }
}
