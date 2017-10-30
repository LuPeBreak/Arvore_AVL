
package arvore;

public class Arvore {

    public static void main(String[] args) {
        No no =new No();
        
        
        System.out.println("\n\nTeste de arvore ainda sem valores:"+no.Busca(4).resultado);
        
        
        no.inserir(4);
        System.out.println("\n\ninserido 4");
        no.inserir(6);
        System.out.println("inserido 6");
        no.inserir(2);
        System.out.println("inserido 2");
        no.inserir(0);
        System.out.println("inserido 0");
        System.out.println("\n\ndesbalanceamento do no a esquerda da raiz = "+no.getDesbalanceamento());
        no.inserir(1);
        System.out.println("inserido 1");
        System.out.println("Agora o desbalanceamento do no esquerda da raiz deve ser 2 \nE de seu filhoesquerdo deve ser -1");
        System.out.println("Necessario uma dupla rotaçao a esquerda \n(1 rotaçao simples a direita com o filho esquerdo do no esquerdo da raiz e 1 rotaçao simples a esquerda com o no a esquerda da raiz)");
        
        System.out.println("\n\n arvore balanceada\n");
        System.out.println("         "+no.getValor());
        System.out.println("      "+no.filhoEsq.getValor()+"     "+no.FilhoDir.getValor());
        System.out.println("    "+no.filhoEsq.filhoEsq.getValor()+"   "+no.filhoEsq.FilhoDir.getValor());
        
        
        
        System.out.println("\n\nmaior numero:"+no.maior());
        System.out.println("menor numero:"+no.menor());
        no.inserir(9);
        System.out.println("inserido 9");
        System.out.println("novo maior numero:"+no.maior());
        
        System.out.println("\n\n arvore balanceada com o acrescimo do 9\n");
        System.out.println("         "+no.getValor());
        System.out.println("      "+no.filhoEsq.getValor()+"     "+no.FilhoDir.getValor());
        System.out.println("    "+no.filhoEsq.filhoEsq.getValor()+"   "+no.filhoEsq.FilhoDir.getValor()+"     "+no.FilhoDir.FilhoDir.getValor());
        
        System.out.println("\n\nEntre 1 e 10 (nao conta nem 1 nem 10):");
        no.entre(1, 10);
        
        
        System.out.println("\n\nCaminho da busca ate 2");
        no.caminhobusca(2);
        
        
    }
}
