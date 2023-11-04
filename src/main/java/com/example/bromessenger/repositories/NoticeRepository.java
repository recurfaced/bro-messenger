package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
