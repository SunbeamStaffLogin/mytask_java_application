// import React, { useState } from 'react';
// import { useNavigate } from 'react-router-dom';
// import { setUserSession } from '../utils/common';
// import axios from 'axios';

// const Login = props => {
//   const history = useNavigate();
//   const username = useFormInput('');
//   const password = useFormInput('');
//   const [error, setError] = useState(null);
//   const [loading, setLoading] = useState(false);

//   // handle button click of login form
//   const handleLogin = () => {
//     setError(null);
//     setLoading(true);
//     axios.post('http://localhost:4000/users/signin', { username: username.value, password: password.value }).then(response => {
//       setLoading(false);
//       setUserSession(response.data.token, response.data.user);
//       history('/dashboard');
//     }).catch(error => {
//       setLoading(false);
//       if (error.response.status === 401) setError(error.response.data.message);
//       else setError("Something went wrong. Please try again later.");
//     });
//   }

//   return (
//     <div>
//       Login<br /><br />
//       <div>
//         Username<br />
//         <input type="text" {...username} autoComplete="new-password" />
//       </div>
//       <div style={{ marginTop: 10 }}>
//         Password<br />
//         <input type="password" {...password} autoComplete="new-password" />
//       </div>
//       {error && <><small style={{ color: 'red' }}>{error}</small><br /></>}<br />
//       <input type="button" value={loading ? 'Loading...' : 'Login'} onClick={handleLogin} disabled={loading} /><br />
//     </div>
//   );
// }

// const useFormInput = initialValue => {
//   const [value, setValue] = useState(initialValue);

//   const handleChange = e => {
//     setValue(e.target.value);
//   }
//   return {
//     value,
//     onChange: handleChange
//   }
// }

// export default Login;

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { setUserSession } from '../utils/common';
import axios from 'axios';

const Login = props => {
  const history = useNavigate();
  const username = useFormInput('');
  const password = useFormInput('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  // handle button click of login form
  const handleLogin = () => {
    setError(null);
    setLoading(true);
    axios.post('http://localhost:4000/users/signin', { username: username.value, password: password.value }).then(response => {
      setLoading(false);
      setUserSession(response.data.token, response.data.user);
      history('/dashboard');
    }).catch(error => {
      setLoading(false);
      if (error.response.status === 401) setError(error.response.data.message);
      else setError("Something went wrong. Please try again later.");
    });
  }

  return (
    <div style={{ maxWidth: 400, margin: 'auto', marginTop: 50, padding: 20, border: '1px solid #ccc', borderRadius: 5 }}>
      <h2>Login</h2>
      <div>
        <label>Username</label><br />
        <input type="text" {...username} autoComplete="new-password" style={{ width: '100%', padding: 10, marginTop: 5, marginBottom: 10, border: '1px solid #ccc', borderRadius: 3 }} />
      </div>
      <div>
        <label>Password</label><br />
        <input type="password" {...password} autoComplete="new-password" style={{ width: '100%', padding: 10, marginTop: 5, marginBottom: 10, border: '1px solid #ccc', borderRadius: 3 }} />
      </div>
      {error && <><small style={{ color: 'red' }}>{error}</small><br /></>}<br />
      <input type="button" value={loading ? 'Loading...' : 'Login'} onClick={handleLogin} disabled={loading} style={{ width: '100%', padding: 10, backgroundColor: '#007bff', color: '#fff', border: 'none', borderRadius: 3, cursor: 'pointer' }} /><br />
    </div>
  );
}

const useFormInput = initialValue => {
  const [value, setValue] = useState(initialValue);

  const handleChange = e => {
    setValue(e.target.value);
  }
  return {
    value,
    onChange: handleChange
  }
}

export default Login;
