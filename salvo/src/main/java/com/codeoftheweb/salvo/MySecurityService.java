package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;
import javax.servlet.http.HttpServletRequest;


@Service("mySecurityService")
public class MySecurityService {

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    public boolean canAccessGameView(HttpServletRequest request, Long requestedPlayerId) {
        Long playerId = (Long) request.getSession().getAttribute("playerId");
        GamePlayer gp = gamePlayerRepository.findById(requestedPlayerId).orElse(null);
        if (playerId == null || !playerId.equals(gp.getPlayers().getUserId())) {
            throw new AccessDeniedException("You are not allowed to see this game");
        }
        return true;
    }
}



