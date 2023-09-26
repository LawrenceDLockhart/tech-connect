import React from 'react';

export enum Technologies {
    JAVA = 'Java',
    PYTHON = 'Python',
    JAVASCRIPT = 'JavaScript',
    // ... (other technologies from your enum)
}

const TechnologySelection: React.FC<{ onChange: (selected: Technologies[]) => void }> = ({ onChange }) => {
    return (
        <select multiple onChange={e => onChange(Array.from(e.target.selectedOptions, option => option.value as Technologies))}>
            {Object.values(Technologies).map(tech => (
                <option key={tech} value={tech}>
                    {tech}
                </option>
            ))}
        </select>
    );
};

export default TechnologySelection;
