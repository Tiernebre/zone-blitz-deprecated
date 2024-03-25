package com.tiernebre.authentication.registration;

import com.tiernebre.util.pagination.Identifiable;

public record Registration(long id, String username, String password)
  implements Identifiable {}
