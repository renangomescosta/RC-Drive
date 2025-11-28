import './Header.css';
import Profile from '../Profile/Profile';
import Menu from './MenuBottom';
import ConectadoIcone from './Conectado.svg';
import DesconectadoIcone from './Desconectado.svg';
import Configuracoes from './configuracoes.png';
import React, { useState, useEffect, useRef } from 'react';

function Header() {
    const [estaConectado, setConectado] = useState(false);

    useEffect(() => {
        setConectado(true); 
    }, []);

    return (
        <header className="header">
            <div>{estaConectado ? 'Conectado' : 'Desconectado'}</div>
            <div className='header-title'>RCDrive</div>
            <img 
                src={estaConectado ? ConectadoIcone : DesconectadoIcone}
                alt={estaConectado ? "Icone de Conectado" : "Icone de desconectado"}
                className="header-icon-connected" 
              />
            <Profile/>
            <Menu/>
        </header>
    );
}







export default Header;