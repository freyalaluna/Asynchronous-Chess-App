import { useState } from 'react';
import { LOG } from '../utils/constants';
import { sendAPIRequest } from '../utils/restfulAPI';
import { getOriginalServerUrl } from '../utils/restfulAPI';

export function useMove(){
    const [validMove, setValidMove] = useState(false);

    const context = {validMove, setValidMove};

    const moveActions = {
        sendMoveRequest: async(sourceSquare, targetSquare, pieceType, gameState) => 
            sendMoveRequest(sourceSquare, targetSquare, pieceType, gameState, context),
    };

    return {validMove, setValidMove, moveActions}
}

async function sendMoveRequest(sourceSquare, targetSquare, pieceType, gameState, context){
    var accountResponse = await sendAPIRequest({requestType: "move", 
                                                sourceSquare: sourceSquare, 
                                                targetSquare: targetSquare,
                                                piece: pieceType,
                                                gameState: gameState}, getOriginalServerUrl());
    if(accountResponse != null && accountResponse.isLegalMove){
        context.setValidMove(true);
        console.log("Move is legal");
    } else {
        context.setValidMove(false);
        LOG.error("Move is not legal");
    }
    return context.validMove;
}