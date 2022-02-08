<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<title><c:out value="${gm.name}"></c:out></title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/style.css"></c:url>" />
        <link rel="shortcut icon" href="#">
        <script src="<c:url value="/inc/js/script.js"></c:url>"></script>
	</head>
	<body>
		<%@ include file="sidenav.jsp" %>
		<div class="content">
			<div class="tabs">
				<button class="tab-button button" onclick="showTab('recap-tab')"><span class="icofont-file-fill"></span><span> <c:out value="${gm.name}"></c:out></span></button>
				<button class="tab-button button" onclick="showTab('niveau-tab')"><span class="icofont-safety"></span><span> Sécurisation du niveau</span></button>
				<button class="tab-button button" onclick="showTab('rush-tab')"><span class="icofont-runner-alt-1"></span><span> Rush sur plusieurs niveau</span></button>
			</div>
			<div id="recap-tab" class="tab" <c:if test="${paramTab != '0'}">style="display: none;"</c:if>>
				<div class="presentation">
					<div>
						<img class="image-gm" src="<c:url value="/inc/img/gm/${gm.image}"></c:url>">
					</div>
					<div>
						${svgGM}
					</div>
					<div class="recap-competence">
						<div>
						<img class="image-competence" src="<c:url value="/inc/img/competence/${gm.competenceGM1.image}"></c:url>">
						<span><c:out value="${gm.competenceGM1.name}"></c:out> : <c:out value="${gm.competenceGM1.description}"></c:out></span>
						</div>
						<c:if test="${gm.competenceGM2.id != 0}">
							<div>
								<img class="image-competence" src="<c:url value="/inc/img/competence/${gm.competenceGM2.image}"></c:url>">
								<span><c:out value="${gm.competenceGM2.name}"></c:out> : <c:out value="${gm.competenceGM2.description}"></c:out></span>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<div id="niveau-tab" class="tab" <c:if test="${paramTab != '1'}">style="display: none;"</c:if>>
				<form method="post" action="GestionRecompensesGM?paramTab=1&idAge=<c:out value="${age.id}"></c:out>&idGM=<c:out value="${gm.id}"></c:out>">
					<div class="input-niveau">
						<label class="form-label" for=niveau>Niveau</label>
						<input class="form-field" type="number" id="niveau" name="niveau" value="<c:out value="${recompensesGM.niveau}"></c:out>">
						<input class="form-submit" type="submit" value="Valider">
						<c:set var="secuTotal" value="${recompensesGMBonus.secuP1 + recompensesGMBonus.secuP2 + recompensesGMBonus.secuP3 + recompensesGMBonus.secuP4 + recompensesGMBonus.secuP5 + recompensesGMBonus.p5}"></c:set>
						<c:set var="secuTotalPercent" value="${100*secuTotal/recompensesGM.total}"></c:set>
						<% pageContext.setAttribute("secuTotalPercentFormatted", String.format("%.2f", pageContext.findAttribute("secuTotalPercent"))); %>
					</div>
					<div class="table-niveau">
						<table>
							<tr>
								<th>Places</th>
								<th>Récompense base</th>
								<th>Récompense arche</th>
								<th>Taux arche</th>
								<th>Sécurisation</th>
								<th>Cumul sécurisation</th>
							</tr>
							<tr>
								<td>P1</td>
								<td><c:out value="${recompensesGM.p1}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.p1}"></c:out></td>
								<td class="input-bonus-arche"><input class="form-field" type="number" id="bonusArcheP1" name="bonusArcheP1" step="0.1" min="0" value="<c:out value="${bonusArcheP1 == null ? 90.0 : bonusArcheP1}"></c:out>"> %</td>
								<td><c:out value="${recompensesGMBonus.secuP1}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.secuP1}"></c:out></td>
							</tr>
							<tr>
								<td>P2</td>
								<td><c:out value="${recompensesGM.p2}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.p2}"></c:out></td>
								<td class="input-bonus-arche"><input class="form-field" type="number" id="bonusArcheP2" name="bonusArcheP2" step="0.1" min="0" value="<c:out value="${bonusArcheP2 == null ? 90.0 : bonusArcheP2}"></c:out>"> %</td>
								<td><c:out value="${recompensesGMBonus.secuP2}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.secuP1 + recompensesGMBonus.secuP2}"></c:out></td>
							</tr>
							<tr>
								<td>P3</td>
								<td><c:out value="${recompensesGM.p3}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.p3}"></c:out></td>
								<td class="input-bonus-arche"><input class="form-field" type="number" id="bonusArcheP3" name="bonusArcheP3" step="0.1" min="0" value="<c:out value="${bonusArcheP3 == null ? 90.0 : bonusArcheP3}"></c:out>"> %</td>
								<td><c:out value="${recompensesGMBonus.secuP3}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.secuP1 + recompensesGMBonus.secuP2 + recompensesGMBonus.secuP3}"></c:out></td>
							</tr>
							<tr>
								<td>P4</td>
								<td><c:out value="${recompensesGM.p4}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.p4}"></c:out></td>
								<td class="input-bonus-arche"><input class="form-field" type="number" id="bonusArcheP4" name="bonusArcheP4" step="0.1" min="0" value="<c:out value="${bonusArcheP4 == null ? 90.0 : bonusArcheP4}"></c:out>"> %</td>
								<td><c:out value="${recompensesGMBonus.secuP4}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.secuP1 + recompensesGMBonus.secuP2 + recompensesGMBonus.secuP3 + recompensesGMBonus.secuP4}"></c:out></td>
							</tr>
							<tr>
								<td>P5</td>
								<td><c:out value="${recompensesGM.p5}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.p5}"></c:out></td>
								<td class="input-bonus-arche"><input class="form-field" type="number" id="bonusArcheP5" name="bonusArcheP5" step="0.1" min="0" value="<c:out value="${bonusArcheP5 == null ? 90.0 : bonusArcheP5}"></c:out>"> %</td>
								<td><c:out value="${recompensesGMBonus.secuP5}"></c:out></td>
								<td><c:out value="${recompensesGMBonus.secuP1 + recompensesGMBonus.secuP2 + recompensesGMBonus.secuP3 + recompensesGMBonus.secuP4 + recompensesGMBonus.secuP5}"></c:out></td>
							</tr>
						</table>
						<label class="form-label">(${secuTotal} PF à poser sur un total de ${recompensesGM.total} PF soit ${secuTotalPercentFormatted}%)</label>
					</div>
				</form>
			</div>
			<div id="rush-tab" class="tab" <c:if test="${paramTab != '2'}">style="display: none;"</c:if>>
				<form method="post" action="GestionRecompensesGM?paramTab=2&idAge=<c:out value="${age.id}"></c:out>&idGM=<c:out value="${gm.id}"></c:out>">
					<div class="input-rush">
						<div class="input-niveau">
							<label class="form-label" for=niveauDepart>Niveau de départ</label>
							<input class="form-field" type="number" id="niveauDepart" name="niveauDepart" value="${niveauDepart}">
						</div>
						<div class="input-niveau">
							<label class="form-label" for=niveauFin>Niveau de fin</label>
							<input class="form-field" type="number" id="niveauFin" name="niveauFin" value="${niveauFin}">
						</div>
						<input class="form-submit" type="submit" value="Valider">
					</div>
					<div class="input-taux-arche">
						<div>
							<label class="form-label" for=bonusArcheRushP1>Taux arche P1</label>
							<span><input class="form-field" type="number" id="bonusArcheRushP1" name="bonusArcheRushP1" step="0.1" min="0" value="<c:out value="${bonusArcheRushP1 == null ? 90.0 : bonusArcheRushP1}"></c:out>"> %</span>
						</div>
						<div>
							<label class="form-label" for=bonusArcheRushP2>Taux arche P2</label>
							<span><input class="form-field" type="number" id="bonusArcheRushP2" name="bonusArcheRushP2" step="0.1" min="0" value="<c:out value="${bonusArcheRushP2 == null ? 90.0 : bonusArcheRushP2}"></c:out>"> %</span>
						</div>
						<div>
							<label class="form-label" for=bonusArcheRushP3>Taux arche P3</label>
							<span><input class="form-field" type="number" id="bonusArcheRushP3" name="bonusArcheRushP3" step="0.1" min="0" value="<c:out value="${bonusArcheRushP3 == null ? 90.0 : bonusArcheRushP3}"></c:out>"> %</span>
						</div>
						<div>
							<label class="form-label" for=bonusArcheRushP4>Taux arche P4</label>
							<span><input class="form-field" type="number" id="bonusArcheRushP4" name="bonusArcheRushP4" step="0.1" min="0" value="<c:out value="${bonusArcheRushP4 == null ? 90.0 : bonusArcheRushP4}"></c:out>"> %</span>
						</div>
						<div>
							<label class="form-label" for=bonusArcheRushP5>Taux arche P5</label>
							<span><input class="form-field" type="number" id="bonusArcheRushP5" name="bonusArcheRushP5" step="0.1" min="0" value="<c:out value="${bonusArcheRushP5 == null ? 90.0 : bonusArcheRushP5}"></c:out>"> %</span>
						</div>
						<div>
							<span><c:out value="${secuTotalRush}"></c:out> PF (sécurisation) / <c:out value="${totalRush}"></c:out> PF (total)</span>
						</div>
					</div>
					<c:if test="${!empty mapRecompensesGM}">
						<div class="table-niveau">
							<table class="table-rush">
								<tr>
									<th>Niveau</th>
									<th>Sécurisation</th>
									<th>Total</th>
									<th>P1</th>
									<th>P2</th>
									<th>P3</th>
									<th>P4</th>
									<th>P5</th>
								</tr>
								<c:forEach items="${mapRecompensesGM}" var="recompensesGM" varStatus="loop">
									<c:set var="secuTotalLoop" value="${recompensesGM.value.secuP1 + recompensesGM.value.secuP2 + recompensesGM.value.secuP3 + recompensesGM.value.secuP4 + recompensesGM.value.secuP5 + recompensesGM.value.p5}"></c:set>
									<tr>
										<td><c:out value="${recompensesGM.value.niveau}"></c:out></td>
										<td><c:out value="${secuTotalLoop}"></c:out></td>
										<td><c:out value="${recompensesGM.value.total}"></c:out></td>
										<td><c:out value="${recompensesGM.value.p1}"></c:out></td>
										<td><c:out value="${recompensesGM.value.p2}"></c:out></td>
										<td><c:out value="${recompensesGM.value.p3}"></c:out></td>
										<td><c:out value="${recompensesGM.value.p4}"></c:out></td>
										<td><c:out value="${recompensesGM.value.p5}"></c:out></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</body>
</html>