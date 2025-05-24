from pydantic import BaseModel

class User_creation_body(BaseModel):
    username: str
    password: str
    
class User_login_body(BaseModel):
    username: str
    password: str