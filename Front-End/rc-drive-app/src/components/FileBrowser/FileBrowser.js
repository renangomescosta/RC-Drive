import React from 'react';
import { setChonkyDefaults, FullFileBrowser, ChonkyActions } from 'chonky';
import { ChonkyIconFA } from 'chonky-icon-fontawesome';

// Configuração única do ícone
setChonkyDefaults({ iconComponent: ChonkyIconFA });

// --- CONFIGURAÇÕES ESTÁTICAS (Traduções e Botões) ---
const i18nPtBr = {
    labels: {
        searchPlaceholder: 'Pesquisar',
        fileCount: (count) => `${count} itens`,
        selected: (count) => `${count} selecionados`,
        noFiles: 'Pasta vazia',
    },
    formatters: {
        formatDate: (date) => new Intl.DateTimeFormat('pt-BR').format(date),
        formatFileSize: (size) => {
            if (size === null) return '0 B';
            const i = Math.floor(Math.log(size) / Math.log(1024));
            return (size / Math.pow(1024, i)).toFixed(2) + ' ' + ['B', 'KB', 'MB', 'GB'][i];
        },
    },
};



const ptBrActions = [
    { ...ChonkyActions.CreateFolder, button: { ...ChonkyActions.CreateFolder.button, name: 'Nova Pasta' } },
    { ...ChonkyActions.UploadFiles, button: { ...ChonkyActions.UploadFiles.button, name: 'Enviar' } },
    { ...ChonkyActions.DownloadFiles, button: { ...ChonkyActions.DownloadFiles.button, name: 'Baixar', group: 'Ações' } },
    { ...ChonkyActions.DeleteFiles, button: { ...ChonkyActions.DeleteFiles.button, name: 'Excluir', group: 'Ações' } },
    { ...ChonkyActions.EnableListView, button: { ...ChonkyActions.EnableListView.button, name: 'Lista', group: 'Opções' } },
    { ...ChonkyActions.EnableGridView, button: { ...ChonkyActions.EnableGridView.button, name: 'Grade', group: 'Opções' } },
    { ...ChonkyActions.SortFilesByName, button: { ...ChonkyActions.SortFilesByName.button, name: 'Nome', group: 'Opções' } },
    { ...ChonkyActions.SortFilesBySize, button: { ...ChonkyActions.SortFilesBySize.button, name: 'Tamanho', group: 'Opções' } },
    { ...ChonkyActions.SortFilesByDate, button: { ...ChonkyActions.SortFilesByDate.button, name: 'Data', group: 'Opções' } },
    { ...ChonkyActions.ToggleHiddenFiles, button: { ...ChonkyActions.ToggleHiddenFiles.button, name: 'Mostrar Ocultos', group: 'Opções' } },
    { ...ChonkyActions.OpenSelection, button: { ...ChonkyActions.OpenSelection.button, name: 'Abrir', group: 'Ações' } },
    { ...ChonkyActions.SelectAllFiles, button: { ...ChonkyActions.SelectAllFiles.button, name: 'Selecionar Tudo', group: 'Ações' } },
    { ...ChonkyActions.ClearSelection, button: { ...ChonkyActions.ClearSelection.button, name: 'Limpar Seleção', group: 'Ações' } },
];

export const MyFileBrowser = () => {
  // Dados Mockados (No futuro virão do Backend via props ou useEffect)
    const files = [
        { id: 'p1', name: 'Código_Fonte', isDir: true, modDate: new Date(), childrenCount: 15 },
        { id: 'p2', name: 'Assets', isDir: true, modDate: new Date(), childrenCount: 8 },
        { id: 'p3', name: 'arquitetura.png', modDate: new Date(), size: 1024 * 1024 },
        { id: 'p5', name: 'main.py', modDate: new Date(), size: 4 * 1024 },
    ];

    const folderChain = [
        { id: 'root', name: 'Meu Drive', isDir: true },
        { id: 'docs', name: 'Documentos', isDir: true },
        { id: '1', name: 'Projetos', isDir: true },
    ];

  // --- AQUI ESTÁ A LÓGICA DE CONEXÃO COM O BACKEND ---
  const handleFileAction = (data) => {
      // data contém: { id: "nome_da_acao", action: { ... }, payload: { ... }, state: { ... } }
      
        switch (data.id) {
            case ChonkyActions.CreateFolder.id:
                console.log('--- [BACKEND] CRIAR PASTA ---');
                // O Chonky não envia o nome da pasta aqui, você precisará abrir um Modal seu
                console.log('Ação: Abrir modal de criação de pasta');
                break;

            case ChonkyActions.UploadFiles.id:
                console.log('--- [BACKEND] UPLOAD ---');
                // No payload pode vir arquivos se for drag & drop, ou você abre seu input file
                console.log('Payload:', data.payload);
                break;

            case ChonkyActions.DeleteFiles.id:
                console.log('--- [BACKEND] DELETAR ---');
                // state.selectedFilesForAction contém o que o usuário selecionou
                const arquivosParaDeletar = data.state.selectedFilesForAction;
                console.log('IDs para deletar:', arquivosParaDeletar.map(f => f.id));
                break;

            case ChonkyActions.OpenFiles.id:
                console.log('--- [NAVEGAÇÃO] ABRIR PASTA/ARQUIVO ---');
                const alvo = data.payload.targetFile || data.payload.files[0];

                if (!alvo) {
                    console.log('Erro: Nenhum arquivo alvo detectado.');
                    break;
                }
                if (alvo.isDir) {
                    console.log(`Navegar para pasta ID: ${alvo.id} - Nome: ${alvo.name}`);
                    // Futuro: setFolderChain(...) e carregar novos arquivos
                } else {
                    console.log(`Abrir visualizador para arquivo: ${alvo.name}`);
                    // Futuro: setFileViewer(alvo)
                }
                break;

            default:
            console.log('Ação genérica acionada:', data.id);
            break;
        }
};

  return (
    <div style={{ height: '100%', minHeight: '400px' }}>
        <FullFileBrowser 
            files={files} 
            folderChain={folderChain} 
            i18n={i18nPtBr} 
            disableDefaultFileActions={true} 
            fileActions={ptBrActions}
            onFileAction={handleFileAction}
        />
    </div>
  );
};