package utfpr.ct.dainf.if62c.avaliacao;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */
public class AvisoFinal extends Aviso {

    public AvisoFinal(Compromisso compromisso) {
        super(compromisso);
    }

    @Override
    public void run() {
        System.out.println(compromisso.getDescricao() + " começa agora.");
        
        for(Aviso a: compromisso.getAvisos())
            a.cancel();
    }
}
