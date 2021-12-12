import React from 'react';
import styled from 'styled-components';

const SignUpButton = () => {
  return(
    <Container>
      <Button>회원가입</Button>
    </Container>
  );
};

const Container = styled.div`
  margin-right: 30px;
`;

const Button = styled.button`
  margin: 0;
  width: 100px;
  height: 35px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  padding: auto;
  border-radius: 20px;
  background: #A17DD9;
  color: #ffffff;

  &:active,
  &:hover,
  &:focus {
    background: #7731E3;
  }

  &:disabled {
    cursor: default;
    opacity: 0.5;
    background: #7731E3;
  }
`;

export default SignUpButton;