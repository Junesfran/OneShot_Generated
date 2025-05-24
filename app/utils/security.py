import hashlib

from json import dumps, loads
from uuid import uuid4
import datetime
import os
import base64
import jwt

from cryptography.hazmat.primitives import padding
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend

from app.config.settings import jwt_key, private_key

def hash_string_as_string(string: str) -> str:
    return hashlib.sha512(string.encode("utf-8")).hexdigest()

def hash_string_as_binary(string: str) -> bytes:
    hashlib.sha512(string("utf-8")).digest()
     
def encrypt_aes256(data: str) -> str:
    iv = os.urandom(16)
    
    padder = padding.PKCS7(128).padder()
    padded_data = padder.update(data.encode("utf-8")) + padder.finalize()
    
    cipher = Cipher(algorithm=algorithms.AES256(private_key), mode=modes.CBC(iv), backend=default_backend())
    encryptor = cipher.encryptor()
    cipher_text = encryptor.update(padded_data) + encryptor.finalize()
    
    return base64.b64encode(iv + cipher_text).decode("utf-8")
    
def decrypt_aes256(data: str) -> str:
    based_data = base64.b64decode(data)
    
    iv = based_data[:16]
    cipher_text = based_data[16:]
    
    cipher = Cipher(algorithm=algorithms.AES256(private_key), mode=modes.CBC(iv), backend=default_backend())
    decryptor = cipher.decryptor()
    padded_plain_text = decryptor.update(cipher_text) + decryptor.finalize()
    
    unpadder = padding.PKCS7(128).unpadder()
    plain_text = unpadder.update(padded_plain_text) + unpadder.finalize()
    
    return plain_text.decode("utf-8")
    
    
def gen_jwt(user_id: int, username: str, extra_payload: dict = None, expiry: int = None) -> str:
    jwt_payload: dict = {
        "user_id": user_id,
        "username": username,
        "random_value": uuid4().hex
    }
    
    if(extra_payload):
        jwt_payload["custom_payload"] = extra_payload
        
    crypted_payload = encrypt_aes256(dumps(jwt_payload))
    
    jwt_content = {
        "data": crypted_payload
    }
    
    if(expiry):
        jwt_content["exp"] = datetime.datetime.utcnow() + datetime.timedelta(minutes=expiry)
        
    built_token = jwt.encode(jwt_content, jwt_key, algorithm="HS256")
    
    return built_token

def verify_and_extract_token(token: str) -> dict:
    # paso completamente de hacer expiry
    
    jwt_content = jwt.decode(token, jwt_key, "HS256")
    
    jwt_encrypted_payload = jwt_content.get("data")
    
    if(jwt_encrypted_payload == None):
        raise KeyError("The provided token is missing critical data")
    
    decrypted_text = decrypt_aes256(jwt_encrypted_payload)
    
    token_payload = loads(decrypted_text)
    
    return token_payload
    