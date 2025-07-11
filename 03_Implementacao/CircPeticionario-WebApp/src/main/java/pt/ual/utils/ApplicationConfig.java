 package pt.ual.utils;
 import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import pt.ual.views.*;

 @ApplicationPath("webresources")
 public class ApplicationConfig extends Application {
   public Set<Class<?>> getClasses() {
     Set<Class<?>> resources = new HashSet<>();
     resources.add(CapitaniaService.class);
     resources.add(ComarcaService.class);
     resources.add(Conselheiros_consultaService.class);
     resources.add(ConsultaService.class);
     resources.add(Destinatario_respostaService.class);
     resources.add(FreguesiaService.class);
     resources.add(Instado_respostaService.class);
     resources.add(MandadoService.class);
     resources.add(Oficio_agregadorService.class);
     resources.add(Oficio_tituloService.class);
     resources.add(Palavra_chaveService.class);
     resources.add(Pessoas_citadasService.class);
     resources.add(ProvocacaoService.class);
     resources.add(Referencia_documentalService.class);
     resources.add(RegistroService.class);
     resources.add(Relac_conselheiros_consultaService.class);
     resources.add(Relac_consulta_consultaService.class);
     resources.add(Relac_consulta_provocacaoService.class);
     resources.add(Relac_consulta_respostaService.class);
     resources.add(Relac_destinatario_respostaService.class);
     resources.add(Relac_instado_respostaService.class);
     resources.add(Relac_mandado_consultaService.class);
     resources.add(Relac_mandado_provocacaoService.class);
     resources.add(Relac_mandado_respostaService.class);
     resources.add(Relac_mandado_ultramarService.class);
     resources.add(Relac_marcador_socio_juridico_pessoaService.class);
     resources.add(Relac_oficio_pessoaService.class);
     resources.add(Relac_pchave_consultaService.class);
     resources.add(Relac_pchave_mandadoService.class);
     resources.add(Relac_pchave_provocacaoService.class);
     resources.add(Relac_pchave_respostaService.class);
     resources.add(Relac_pchave_ultramarService.class);
     resources.add(Relac_pcitadas_consultaService.class);
     resources.add(Relac_pcitadas_mandadoService.class);
     resources.add(Relac_pcitadas_ultramarService.class);
     resources.add(Relac_pcitadas_ultramarService.class);
     resources.add(Relac_pcitadas_provocacaoService.class);
     resources.add(Relac_provocacao_provocacaoService.class);
     resources.add(Relac_provocacao_respostaService.class);
     resources.add(Relac_remetenete_provocacaoService.class);
     resources.add(Relac_remetente_provocacaoService.class);
     resources.add(Relac_requerente_consultaService.class);
     resources.add(Relac_requerente_mandadoService.class);
     resources.add(Relac_requerente_provocacaoService.class);
     resources.add(Relac_requerente_respostaService.class);
     resources.add(Relac_requerente_ultramarService.class);
     resources.add(Relac_resposta_respostaService.class);
     resources.add(Relac_secretario_conselho_respostaService.class);
     resources.add(Relac_tema_consultaService.class);
     resources.add(Relac_tema_provocacaoService.class);
     resources.add(Relac_tema_respostaService.class);
     resources.add(Relac_tema_ultramarService.class);
     resources.add(Relac_tema_mandadoService.class);
     resources.add(Relac_termo_freguesiaService.class);
     resources.add(Relac_termo_provocacaoService.class);
     resources.add(Relac_ultramar_consultaService.class);
     resources.add(Relac_ultramar_provocacaoService.class);
     resources.add(Relac_ultramar_respostaService.class);
     resources.add(RemetenteService.class);
     resources.add(RequerenteService.class);
     resources.add(RespostaService.class);
     resources.add(Secretario_conselho_respostaService.class);
     resources.add(TemaService.class);
     resources.add(TermoService.class);
     resources.add(Tipo_referencia_documentalService.class);
     resources.add(Tipologia_diplomaticaService.class);
     resources.add(UltramarService.class);
     resources.add(UtilizadoresService.class);
     resources.add(PessoaService.class);
     resources.add(Marcador_economico_ocupacaoService.class);
     resources.add(Relac_ultramar_ultramarService.class);
     resources.add(Relac_mandado_mandadoService.class);
     resources.add(Relac_marcador_economico_ocupacao_pessoaService.class);
     resources.add(Marcador_status_socio_juridico_pessoaService.class);
     resources.add(Relac_termo_comarcaService.class);
     resources.add(Relac_capitania_consultaService.class);
     resources.add(Relac_capitania_mandadoService.class);
     resources.add(Relac_capitania_provocacaoService.class);
     resources.add(Relac_capitania_respostaService.class);
     resources.add(Relac_capitania_ultramarService.class);
     resources.add(Relac_comarca_consultaService.class);
     resources.add(Relac_comarca_mandadoService.class);
     resources.add(Relac_comarca_provocacaoService.class);
     resources.add(Relac_comarca_respostaService.class);
     resources.add(Relac_comarca_ultramarService.class);
     resources.add(Relac_freguesia_consultaService.class);
     resources.add(Relac_freguesia_mandadoService.class);
     resources.add(Relac_freguesia_provocacaoService.class);
     resources.add(Relac_freguesia_respostaService.class);
     resources.add(Relac_freguesia_ultramarService.class);
     resources.add(Relac_termo_consultaService.class);
     resources.add(Relac_termo_mandadoService.class);
     resources.add(Relac_termo_respostaService.class);
     resources.add(Relac_termo_ultramarService.class);
     resources.add(Tema_agregadorService.class);
     resources.add(Secretario_conselheiroService.class);
     resources.add(Secretario_agregadorService.class);
     resources.add(Relac_secretario_conselho_provocacaoService.class);
     resources.add(Relac_secretario_conselho_mandadoService.class);
     resources.add(Relac_secretario_conselho_consultaService.class);
     resources.add(Relac_secretario_conselho_ultramarService.class);

     return resources;
   }
 }