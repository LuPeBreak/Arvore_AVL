package arvore;

public class No {

    public No filhoEsq, FilhoDir, Pai;
    private Integer valor;
    private int h = 1, desbalanceamento = 0;

    //funçoes primarias
    public void inserir(int valor) {
        ResultadoBusca x;
        x = this.Busca(valor);
        if (x.resultado == SaidaBusca.CHAVE_ENCONTRADA) {
            System.out.println("chave ja existente impossivel inserir novamente");
        } else if (x.resultado == SaidaBusca.ARVORE_VAZIA) {
            x.no.setValor(valor);
        } else if (x.resultado == SaidaBusca.CHAVE_NAO_ENCONTRADA_ESQ) {
            No pt1 = new No();
            pt1.valor = valor;
            x.no.filhoEsq = pt1;
            x.no.filhoEsq.Pai = x.no;
            x.no.filhoEsq.CalcH();
            x.no.filhoEsq.CalcDesbalanceamento();
            x.no.filhoEsq.Balancear();

        } else {
            No pt1 = new No();
            pt1.valor = valor;
            x.no.FilhoDir = pt1;
            x.no.FilhoDir.Pai = x.no;
            x.no.FilhoDir.CalcH();
            x.no.FilhoDir.CalcDesbalanceamento();
            x.no.FilhoDir.Balancear();

        }
    }
    public ResultadoBusca Busca(int valor) {
        // verifica se a arvore esta vazia
        if (this.valor == null) {
            ResultadoBusca result = new ResultadoBusca();
            result.no = this;
            result.resultado = SaidaBusca.ARVORE_VAZIA;
            return result;
        // verifica se encontrou o valor
        } else if (this.valor == valor) {
            ResultadoBusca result = new ResultadoBusca();
            result.no = this;
            result.resultado = SaidaBusca.CHAVE_ENCONTRADA;
            return result;
        // verifica se o valor procurado é menor que o valor do nó
        } else if (valor < this.valor) {
            // verifica se  o filho a esquerda é nulo
            if (this.filhoEsq == null) {
                ResultadoBusca result = new ResultadoBusca();
                result.no = this;
                result.resultado = SaidaBusca.CHAVE_NAO_ENCONTRADA_ESQ;
                return result;
            } else {
                // continua a busca no filho esquerdo
                return this.filhoEsq.Busca(valor);
            }
        // verifica se o filho direito é nulo
        } else if (this.FilhoDir == null) {
            ResultadoBusca result = new ResultadoBusca();
            result.no = this;
            result.resultado = SaidaBusca.CHAVE_NAO_ENCONTRADA_DIR;
            return result;
        } else {
            // continua a busca no filho direito
            return this.FilhoDir.Busca(valor);
        }
    }

    
    /*
    * Funçoes Internas
    */
    
    //funçao para o calculo da altura da arvore
    private void CalcH() {
        if (this.filhoEsq == null && this.FilhoDir == null) {
            this.setH(1);
        } else if (this.filhoEsq != null && this.FilhoDir != null) {
            if (this.FilhoDir.h >= this.filhoEsq.h) {
                this.setH(this.FilhoDir.h + 1);
            } else {
                this.setH(this.filhoEsq.h + 1);
            }
        } else if (this.filhoEsq == null) {
            this.setH(this.FilhoDir.h + 1);
        } else {
            this.setH(this.filhoEsq.h + 1);
        }
        if (this.Pai != null) {
            this.Pai.CalcH();
        }
    }
    // funçao para balancear a arvore 
    // de acordo com os desbalanceamentos do no e dos filhos
    private void Balancear() {
        // verifica se existe desbalanceamento 
        if (this.getDesbalanceamento() > 1 || this.getDesbalanceamento() < -1) {
            // se o pai tiver desbalanceamento negativo e esta desbalanceado(<-2)
            if (this.getDesbalanceamento() <= -2) {
                // se o filho tambem tiver desbalanceamento negativo
                if (this.FilhoDir.desbalanceamento <= -1) {
                    this.Rotaçao(1, this);
                    this.CalcH();
                    this.CalcDesbalanceamento();
                // se o filho tiver desbalanceamento positivo 
                } else if (this.FilhoDir.desbalanceamento >= 1) {
                    this.Rotaçao(3, this);
                }
            }
            // se o pai tiver desbalanceamento positivo e esta desbalanceado(>-2)
            if (this.getDesbalanceamento() >= 2) {
                // se o filho tambem tiver desbalanceamento positivo
                if (this.filhoEsq.desbalanceamento >= 1) {
                    this.Rotaçao(2, this);
                    this.CalcH();
                    this.CalcDesbalanceamento();
                // se o filho tiver desbalanceamento negativo 
                } else if (this.filhoEsq.desbalanceamento <= -1) {
                    this.Rotaçao(4, this);
                }
            }
        }
        if (this.Pai != null) {
            this.Pai.Balancear();
        }

    }
    // Funçao para calcular o desbalanceamento
    private void CalcDesbalanceamento() {
        // se nao tiver filhos o desbalanceamento é 0
        if (this.filhoEsq == null && this.FilhoDir == null) {
            this.setDesbalanceamento(0);
        }// se tiver os 2 filhos 
        //desbalanceamento = alturas do FilhoEsq - a do FilhoDir 
        else if (this.filhoEsq != null && this.FilhoDir != null) { 
            this.setDesbalanceamento(this.filhoEsq.h - this.FilhoDir.h);
        } 
        // se so tiver o filho direito 
        //desbalancealemnto = - altura do filho direito
        else if (this.filhoEsq == null) {
            this.setDesbalanceamento(-this.FilhoDir.h);
        } 
        // se so tiver filho esquerdo desb= altura do filho esquerdo
        else {
            this.setDesbalanceamento(this.filhoEsq.h);
        }
        // se esse no tiver um pai ( nao for raiz ) executa o calculo no pai 
        if (this.Pai != null) {
            this.Pai.CalcDesbalanceamento();
        }
    }
    
    //funçao para executar as rotaçoes
    private void Rotaçao(int x, No inicio) {
        switch (x) {
            //rotaçao simples a esquerda
            case 1:
                No dir = inicio.FilhoDir;
                dir.setPai(inicio.Pai);
                inicio.setFilhoDir(dir.getFilhoEsq());

                if (inicio.getFilhoDir() != null) {
                    inicio.getFilhoDir().setPai(inicio);
                }

                dir.setFilhoEsq(inicio);
                inicio.setPai(dir);

                if (dir.getPai() != null) {

                    if (dir.getPai().getFilhoDir() == inicio) {
                        dir.getPai().setFilhoDir(dir);

                    } else if (dir.getPai().getFilhoEsq() == inicio) {
                        dir.getPai().setFilhoEsq(dir);
                    }
                }
                break;
            // Rotaçao simples a direita    
            case 2:
                No esq = inicio.filhoEsq;
                esq.setPai(inicio.Pai);
                inicio.setFilhoEsq(esq.getFilhoDir());

                if (inicio.getFilhoEsq() != null) {
                    inicio.getFilhoEsq().setPai(inicio);
                }

                esq.FilhoDir = inicio;
                inicio.setPai(esq);

                if (esq.getPai().getFilhoDir() == inicio) {
                    esq.getPai().setFilhoDir(esq);

                } else if (esq.getPai().getFilhoEsq() == inicio) {
                    esq.getPai().setFilhoEsq(esq);
                }
                break;
            // Rotaçao dupla a esquerda
            case 3:
                inicio.Rotaçao(2, inicio.FilhoDir);
                inicio.CalcH();
                inicio.CalcDesbalanceamento();
                inicio.Rotaçao(1, inicio);
                break;
            // Rotaçao dupla a direita
            case 4:
                inicio.Rotaçao(1, inicio.filhoEsq);
                inicio.CalcH();
                inicio.CalcDesbalanceamento();
                inicio.Rotaçao(2, inicio);
                break;
        }
    }

    
    /*
    * Funçoes extras
    */
    // Funçao que retorna o menor valor
    public int menor() {
        if (this.filhoEsq == null) {
            return this.getValor();
        } else {
            return this.filhoEsq.menor();
        }
    }
    // Funçao que retorna o maior valor
    public int maior() {
        if (this.FilhoDir == null) {
            return this.getValor();
        } else {
            return this.FilhoDir.maior();
        }
    }
    // Funçao que retorna todos os valores existentes na tabela 
    // que estejam entre os valores pesquisados
    public void entre(int min, int max) {
        ResultadoBusca x;
        for (int y = min + 1; y < max; y++) {
            x = this.Busca(y);
            if (x.resultado == SaidaBusca.CHAVE_ENCONTRADA) {
                System.out.println(x.no.getValor());
            }
        }
    }
    //funçao que mostra todos os nós que passa ate chegar o valor buscado
    public void caminhobusca(int valor) {
        if (this.Busca(valor).resultado!= SaidaBusca.CHAVE_ENCONTRADA){
            System.out.println("Valor nao existente na arvore");
        }else if(this.getValor()==valor){
            System.out.println(this.getValor());
        }else if(this.getValor()> valor){
            System.out.println(this.getValor());
            this.filhoEsq.caminhobusca(valor);
        }else {
            System.out.println(this.getValor());
            this.FilhoDir.caminhobusca(valor);
        }
    }
    
    
    /*
    * Funçoes getters e Setters
    */
    public int getDesbalanceamento() {
        return desbalanceamento;
    }
    public void setDesbalanceamento(int desbalanceamento) {
        this.desbalanceamento = desbalanceamento;
    }
    public int getH() {
        return h;
    }
    public void setH(int h) {
        this.h = h;
    }
    public No getFilhoEsq() {
        return filhoEsq;
    }
    public void setFilhoEsq(No filhoEsq) {
        this.filhoEsq = filhoEsq;
    }
    public No getFilhoDir() {
        return FilhoDir;
    }
    public void setFilhoDir(No FilhoDir) {
        this.FilhoDir = FilhoDir;
    }
    public No getPai() {
        return Pai;
    }
    public void setPai(No Pai) {
        this.Pai = Pai;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }

}
