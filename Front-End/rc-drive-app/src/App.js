import { useState } from 'react';
import Header from './components/Header/Header';

// --- 1. AGRUPE TODOS OS IMPORTS NO TOPO ---
import { setChonkyDefaults, FullFileBrowser } from 'chonky';
import { ChonkyIconFA } from 'chonky-icon-fontawesome';

// --- 2. CONFIGURE O CHONKY LOGO ABAIXO DOS IMPORTS ---
setChonkyDefaults({ iconComponent: ChonkyIconFA });

// --- 3. SEU COMPONENTE DE ARQUIVOS ---
export const MyFileBrowser = () => {
    const files = [
        { id: 'lht', name: 'Projects', isDir: true },
        {
            id: 'mcd',
            name: 'chonky-sphere-v2.png',
            thumbnailUrl: 'https://chonky.io/chonky-sphere-v2.png',
        },
    ];
    const folderChain = [{ id: 'xcv', name: 'Demo', isDir: true }];
    
    // O Chonky precisa de altura definida no pai para aparecer
    return (
        <div style={{ height: 300 }}>
            <FullFileBrowser files={files} folderChain={folderChain} />
        </div>
    );
};

const perfil = 'Renan'; 

// --- 4. O APP PRINCIPAL ---
export default function App() {
  return (
    <div>
      <div><Header /></div>
      
      <div style={{ display: 'flex', gap: '20px', padding: '20px' }}>
        <div>Resumo Nuvem</div>
        
        {/* AQUI ESTÁ O PULO DO GATO: Você precisa chamar o componente aqui */}
        <div style={{ flex: 1 }}> 
            <MyFileBrowser />
        </div>
      </div>
    </div>
  );
}