from pydantic import BaseModel

class Token_data(BaseModel):
    user_id: int
    username: str
    random_value: str