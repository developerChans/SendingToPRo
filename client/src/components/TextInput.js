import React, { useState } from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const TextInput = ({ text, type = 'text', placeholder, height, value }) => {
  const [val, setValue] = useState(value);

  return (
    <>
      <Section>
        <Text>{text}</Text>
        <Input
          type={type}
          placeholder={placeholder}
          value={val}
          onChange={({ target: { val } }) => {
            setValue(value);
          }}
          required
        />
      </Section>
    </>
  );
};

TextInput.propTypes = {
  text: PropTypes.string.isRequired,
  type: PropTypes.string,
  placeholder: PropTypes.string.isRequired,
  height: PropTypes.string.isRequired,
  value: PropTypes.string,
};

const Section = styled.section`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const Text = styled.span`
  font-weight: 500;
  font-size: 0.9rem;
  color: #383838;
`;

const Input = styled.input`
  margin-top: 10px;
  border: 0px;
  border-bottom: 2px solid #383838;
  padding: 5px;
  outline-style: none;
  background-color: inherit;
`;

export default TextInput;