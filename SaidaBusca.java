package arvore;

//enum para saida da busca padronizada
public enum SaidaBusca {
    ARVORE_VAZIA(0),CHAVE_ENCONTRADA(1), CHAVE_NAO_ENCONTRADA_ESQ(2), CHAVE_NAO_ENCONTRADA_DIR(3);

    int val;

    SaidaBusca(int inp) {

        this.val = inp;

    }

    SaidaBusca() {
    }

    SaidaBusca getValue(int inp) {

        switch (inp) {
            
        case 0:

        return ARVORE_VAZIA;

        case 1:

        return CHAVE_ENCONTRADA;

        case 2:

        return CHAVE_NAO_ENCONTRADA_ESQ;

        case 3:

        return CHAVE_NAO_ENCONTRADA_DIR;

        default:

        return ARVORE_VAZIA;

}

}

}
