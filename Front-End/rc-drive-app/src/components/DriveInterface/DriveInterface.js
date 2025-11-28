import React, { useState, useEffect, useRef } from 'react';
import './Profile.css';

// Dados mocados conforme solicitado
const AVAILABLE_PROFILES = [
  { id: 1, nome: 'Renan', foto: 'https://media.licdn.com/dms/image/v2/D4D03AQHT6p6TC9f_6A/profile-displayphoto-shrink_800_800/B4DZVEem.iHwAc-/0/1740610635254?e=1766016000&v=beta&t=PVs2IjTnVN9h_9K0DIC7EIQSGpTI1mmOAY6NzzqmCIs' },
  { id: 2, nome: 'Ana Julia', foto: 'https://media.licdn.com/dms/image/v2/D4D03AQHEOLFJatv4IA/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1728760916239?e=1766016000&v=beta&t=W3jQ61d5VQ0Mccgkhgw3u0PjoSOZJVdsgKY0OTU6zqc' },
  { id: 3, nome: 'Igor', foto: 'https://media.licdn.com/dms/image/v2/C4D03AQEEYBLuagfmkQ/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1656944473409?e=1766016000&v=beta&t=zz9PdccXUVr33sBJ3q-suDnfiMOK-vZGYJFjPupLg-4' },
];

const Profile = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [currentProfile, setCurrentProfile] = useState(AVAILABLE_PROFILES[0]);
  
  // Ref para detectar clique fora do componente e fechar o dropdown
  const dropdownRef = useRef(null);
  const toggleDropdown = () => setIsOpen(!isOpen);

  const handleSelectProfile = (profile) => {
    setCurrentProfile(profile);
    setIsOpen(false);
    // Aqui você adicionaria a lógica de troca de contexto/auth se necessário
  };

  // Fecha o dropdown se clicar fora dele
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsOpen(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  return (
    <div className="profile-wrapper" ref={dropdownRef}>
      {/* Header Component */}
      <div className="profile-container" onClick={toggleDropdown}>
        <img 
          src={currentProfile.foto} 
          alt="Profile" 
          className="profile-avatar" 
        />
        <span className="profile-name">{currentProfile.nome}</span>
      </div>

      {/* Dropdown List */}
      {isOpen && (
        <div className="profile-dropdown">
          {AVAILABLE_PROFILES.map((profile) => (
            <div 
              key={profile.id} 
              className="dropdown-item" 
              onClick={() => handleSelectProfile(profile)}
            >
              <img 
                src={profile.foto} 
                alt={profile.nome} 
                className="dropdown-avatar" 
              />
              <span className="dropdown-name">{profile.nome}</span>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Profile;