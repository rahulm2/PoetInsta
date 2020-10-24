package com.mehra9.poetinsta.service;
import java.util.List;

import com.mehra9.poetinsta.dto.CommentResponse;
import com.mehra9.poetinsta.dto.PoemResponse;
import com.mehra9.poetinsta.models.Comment;
import com.mehra9.poetinsta.models.Poem;
import com.mehra9.poetinsta.models.User;

public interface PoemService {
    
    List<PoemResponse> getPoems();
    PoemResponse createPoem(Poem poem);
    List<PoemResponse> getPoemByUser(User user);
    PoemResponse getPoemById(Long id);
    CommentResponse addComment(Comment comment);
    Poem getPoemModelById(Long id);
    List<CommentResponse> getCommentsByPoem(Long poemId);
    PoemResponse updatePoemById(Long poemId, PoemResponse poemResponse);

}
