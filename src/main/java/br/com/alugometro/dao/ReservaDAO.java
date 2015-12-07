package br.com.alugometro.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.alugometro.domain.Reserva;
import br.com.alugometro.domain.Reserva.SituacaoReserva;

@Repository
public class ReservaDAO extends AbstractDAO {

	public Reserva buscarPorId(Long idReserva) {
		return em.find(Reserva.class, idReserva);
	}

	public List<Reserva> buscarPorIdUsuario(Long idUsuario) {
		return em.createQuery("FROM Reserva WHERE Id_Usuario = :idUsuario", Reserva.class)
				.setParameter("idUsuario", idUsuario).getResultList();
	}

	public List<Reserva> listarPorDataESituacao(String dataInicio, String dataFim, SituacaoReserva situacaoReserva) {

		StringBuilder sql = new StringBuilder("FROM Reserva r WHERE 1=1");

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		// AND r.data_FIM <= TO_DATE('2015-12-18', 'yyyy-MM-dd');
		
		if (dataInicio != null) {
			sql.append(" AND r.dataInicio >= TO_DATE(:dataInicio, 'yyyy-MM-dd')");
			parameters.put("dataInicio", dataInicio);
		}
		if (dataFim != null) {
			sql.append(" AND r.dataFim <= TO_DATE(:dataFim, 'yyyy-MM-dd')");
			parameters.put("dataFim", dataFim);
		}
		if (situacaoReserva != null) {
			sql.append(" AND r.situacao = :situacaoReserva");
			parameters.put("situacaoReserva", Enum.valueOf(SituacaoReserva.class, situacaoReserva.name()));
		}

		TypedQuery<Reserva> query = em.createQuery(sql.toString(), Reserva.class);

		Set set = parameters.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry) iterator.next();
			query.setParameter(mentry.getKey().toString(), mentry.getValue());
		}

		return query.getResultList();
	}
	
	public List<SituacaoReserva> listarSituacoes() {
		return em.createQuery("FROM Reserva", SituacaoReserva.class)
			.getResultList();
	}

	@Transactional
	public void cancelar(Long idReserva) {
		em.createQuery("UPDATE Reserva SET Id_Situacao_Reserva=3 " + "WHERE Id_Reserva = :idReserva")
				.setParameter("idReserva", idReserva).executeUpdate();
	}

	@Transactional
	public Reserva salvar(Reserva entidade) {
		if (entidade.getIdReserva() == null) {
			em.persist(entidade);
			return entidade;
		}

		return em.merge(entidade);
	}
	
	public List<Reserva> buscarReservaPorPeriodoESituacao(Reserva reserva){
		return em.createQuery("FROM Reserva WHERE Data_Inicio = :dataInicio AND Data_Fim = :dataFim AND Id_Situacao_Reserva != 4")
				  .setParameter("dataInicio", reserva.getDataInicio())
				  .setParameter("dataFim", reserva.getDataFim())
			   	  .getResultList();
	}
}
