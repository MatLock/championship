package com.santex.football.championship.repository;

import com.santex.football.championship.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampionshipRepository extends JpaRepository<Competition,String> {

  String COUNT_PLAYERS = "SELECT COUNT(*) FROM COMPETITION c" +
    " JOIN COMPETITION_TEAM tc ON tc.COMPETITION_ID = c.ID" +
    " JOIN PLAYER p ON p.TEAM_ID = tc.TEAM_ID" +
    " WHERE p.role = 'PLAYER'" +
    " AND c.code = :code";

  /**
   *  finds a competition by its ID
   * @param id
   * @return
   */
  Optional<Competition> findById(String id);

  /**
   * finds a competition by code
   * @param code
   * @return
   */
  Optional<Competition> findByCode(String code);

  /**
   * check whether the competition exists or not
   * @param code
   * @return
   */
  Boolean existsByCode(String code);

  /**
   * count all the players of the teams within the competition
   * @param code
   * @return
   */
  @Query(value = COUNT_PLAYERS,nativeQuery = true)
  Integer countPlayers(@Param("code") String code);


}
