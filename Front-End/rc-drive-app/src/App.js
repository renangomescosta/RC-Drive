import React from 'react';
import Header from './components/Header/Header';
import { MyFileBrowser } from './components/FileBrowser/FileBrowser';

export default function App() {
  return (
    <div style={{ height: '100vh', display: 'flex', flexDirection: 'column' }}>
      <Header />
      
      <div style={{ display: 'flex', flex: 1, gap: '20px', padding: '20px', backgroundColor: '#f5f5f5' }}>
        
        {/* Barra Lateral (Mockada por enquanto) */}
        <div style={{ width: '200px', backgroundColor: 'white', padding: '15px', borderRadius: '8px' }}>
          <h3>Resumo Nuvem</h3>
          <p>4GB usados de 15GB</p>
        </div>
        
        {/* Área Principal com o File Browser */}
        <div style={{ flex: 1, backgroundColor: 'white', borderRadius: '8px', overflow: 'hidden' }}> 
            <MyFileBrowser />
        </div>
      </div>
    </div>
  );
}